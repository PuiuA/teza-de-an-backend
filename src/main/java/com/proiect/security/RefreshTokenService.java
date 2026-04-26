package com.proiect.security;

import com.proiect.exception.TokenException;
import com.proiect.model.Admin;
import com.proiect.model.RefreshToken;
import com.proiect.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration; // ms, ex: 604800000 = 7 zile

    @Transactional
    public RefreshToken createRefreshToken(Admin admin) {
        // Revocam toate token-urile vechi ale adminului
        refreshTokenRepository.revokeAllByAdmin(admin);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .admin(admin)
                .expiryDate(Instant.now().plusMillis(refreshTokenExpiration))
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenException("Refresh token invalid sau inexistent"));

        if (refreshToken.isRevoked()) {
            throw new TokenException("Refresh token a fost revocat");
        }

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new TokenException("Refresh token a expirat. Te rugăm să te autentifici din nou");
        }

        return refreshToken;
    }
}