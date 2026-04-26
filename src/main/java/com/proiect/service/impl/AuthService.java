package com.proiect.service.impl;

import com.proiect.dto.*;
import com.proiect.model.Admin;
import com.proiect.model.AdminRole;
import com.proiect.model.RefreshToken;
import com.proiect.repository.AdminRepository;
import com.proiect.security.JwtService;
import com.proiect.security.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String accessToken = jwtService.generateAccessToken(admin);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(admin);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .role(admin.getRole().name())
                .username(admin.getUsername())
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        Admin admin = refreshToken.getAdmin();
        String newAccessToken = jwtService.generateAccessToken(admin);

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken.getToken())
                .role(admin.getRole().name())
                .username(admin.getUsername())
                .build();
    }

    public void createAdmin(CreateAdminRequest request) {
        if (adminRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username-ul este deja folosit");
        }

        Admin admin = Admin.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(AdminRole.ADMIN) // Mereu ADMIN, nu SUPER_ADMIN
                .build();

        adminRepository.save(admin);
    }

    public AuthResponse getInfoFromToken(String token) {
        String username = jwtService.extractUsername(token);
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Admin negasit"));
        return AuthResponse.builder()
                .role(admin.getRole().name())
                .username(admin.getUsername())
                .build();
    }
}