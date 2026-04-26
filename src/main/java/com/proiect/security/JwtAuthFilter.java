package com.proiect.security;

import com.proiect.repository.AdminRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AdminRepository adminRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String jwtFromCookie = null;
        String jwtFromHeader = null;

        // 1. Cookie
        if (request.getCookies() != null) {
            jwtFromCookie = Arrays.stream(request.getCookies())
                    .filter(c -> "access_token".equals(c.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }

        // 2. Authorization header
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtFromHeader = authHeader.substring(7);
        }

        // Prioritate: header cookie
        String jwt = jwtFromHeader != null ? jwtFromHeader : jwtFromCookie;

        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final boolean fromHeader = jwtFromHeader != null;

        try {
            final String username = jwtService.extractUsername(jwt);
            System.out.println("=JWT username: [" + username + "]");
            System.out.println("=Admin găsit: " + adminRepository.findByUsername(username).orElse(null));
            System.out.println("=Token valid: " + (adminRepository.findByUsername(username).map(a -> jwtService.isTokenValid(jwt, a)).orElse(false)));
            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {
                var admin = adminRepository.findByUsername(username).orElse(null);
                if (admin != null && jwtService.isTokenValid(jwt, admin)) {
                    var authToken = new UsernamePasswordAuthenticationToken(
                            admin, null, admin.getAuthorities());
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (ExpiredJwtException e) {
            if (fromHeader) {
                // Token din header expirat  401 pentru refresh
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Token expirat\"}");
                return;
            }
            // Token din cookie expirat  ignora, continua
        } catch (Exception e) {
            System.out.println("=AUTH ERROR: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}