package com.rtc.app.auth.service.authentication;

import com.rtc.app.auth.repository.RefreshTokenRepository;
import com.rtc.app.auth.dto.response.LoginResponse;
import com.rtc.app.auth.dto.internal.RefreshTokenWithExpire;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthTokenService {

    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginResponse issueTokens(Long userId, String email, String type) {
        String accessToken = jwtService.generateAccessToken(userId, email, type);
        RefreshTokenWithExpire refreshToken = jwtService.generateRefreshToken(userId);

        Duration ttl = Duration.ofMillis(Math.max(0, refreshToken.getExpireAt().getTime() - System.currentTimeMillis()));
        refreshTokenRepository.save(refreshToken.getRefreshToken(), userId, ttl);

        return new LoginResponse(accessToken, refreshToken.getRefreshToken());
    }
}
