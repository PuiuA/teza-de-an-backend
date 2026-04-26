package com.proiect.controller;

import com.proiect.dto.*;
import com.proiect.exception.TokenException;
import com.proiect.service.impl.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        AuthResponse auth = authService.login(request);
        return ResponseEntity.ok(auth); // returneaza tot, inclusiv tokenurile
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(
            @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me(
            @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }
        String token = authHeader.substring(7);
        return ResponseEntity.ok(authService.getInfoFromToken(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        addCookie(response, "access_token",  "", "/", 0);
        addCookie(response, "refresh_token", "/api/auth/refresh", "", 0);
        return ResponseEntity.noContent().build();
    }

    private void addCookie(HttpServletResponse response,
                           String name, String value,
                           String path, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        // Adauga SameSite=None pentru cross-origin
        response.addHeader("Set-Cookie",
                name + "=" + value +
                        "; Path=" + path +
                        "; Max-Age=" + maxAge +
                        "; HttpOnly; Secure; SameSite=None");
    }

    private String extractCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return null;
        return Arrays.stream(request.getCookies())
                .filter(c -> name.equals(c.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

    @GetMapping("/hash")
    public String hashPassword(@RequestParam String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}