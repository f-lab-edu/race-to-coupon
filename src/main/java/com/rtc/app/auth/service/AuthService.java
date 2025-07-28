package com.rtc.app.auth.service;

import com.rtc.app.auth.dto.internal.RefreshTokenWithExpire;
import com.rtc.app.auth.dto.request.command.SignUpCommand;
import com.rtc.app.auth.dto.response.SignUpResponse;
import com.rtc.app.auth.entity.User;
import com.rtc.app.auth.exception.DuplicationException;
import com.rtc.app.auth.repository.RefreshTokenRepository;
import com.rtc.app.auth.repository.UserRepository;
import com.rtc.app.auth.service.authentication.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void logout(Long userId) {
        // 시간이 유효한 액세스 토큰을 블랙리스트에 넣으면, 모든 API 요청 마다 해당 토큰이 유효한지 체크 해야 함
        refreshTokenRepository.delete(userId);
    }
    
    public SignUpResponse signUp(SignUpCommand command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new DuplicationException("이미 존재하는 이메일입니다.");
        }

        if (userRepository.existsUserByName(command.getName())) {
            throw new DuplicationException("이미 존재하는 이름입니다.");
        }

        User user = User.create(command.getEmail(), command.getName(), passwordEncoder.encode(command.getPassword()));
        User savedUser = userRepository.save(user);

        // 액세스 토큰 및 리프레시 토큰 생성
        String accessToken = jwtService.generateAccessToken(savedUser.getId(), savedUser.getEmail(), savedUser.getType().getSecurityLevel());
        RefreshTokenWithExpire refreshToken = jwtService.generateRefreshToken(savedUser.getId());

        // TTL 설정 후, Redis 저장
        Duration ttl = Duration.ofMillis(Math.max(0, refreshToken.getExpireAt().getTime() - System.currentTimeMillis()));
        refreshTokenRepository.save(refreshToken.getRefreshToken(), savedUser.getId(), ttl);
        
        return new SignUpResponse(accessToken, refreshToken.getRefreshToken());
    }
}
