package com.rtc.app.auth.service.authentication;

import com.rtc.app.auth.entity.User;
import com.rtc.app.auth.service.query.SecurityQueryService;
import com.rtc.app.auth.dto.internal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SecurityQueryService securityQueryService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = securityQueryService.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        return CustomUserDetails.from(user);
    }
}
