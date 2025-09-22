package com.rtc.app.auth.service.query;

import com.rtc.app.auth.entity.User;
import com.rtc.app.auth.exception.UserNotFoundException;
import com.rtc.app.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 쿠폰 패키지에서 사용
 */
@Service
@RequiredArgsConstructor
public class CouponUserReadService {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다."));
    }
}
