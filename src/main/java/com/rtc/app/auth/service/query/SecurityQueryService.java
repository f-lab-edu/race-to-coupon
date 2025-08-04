package com.rtc.app.auth.service.query;

import com.rtc.app.auth.entity.User;
import com.rtc.app.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * security 패키지 전용 쿼리 서비스
 */
@Service
@RequiredArgsConstructor
public class SecurityQueryService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
