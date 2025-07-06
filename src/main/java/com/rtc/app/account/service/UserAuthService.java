package com.rtc.app.account.service;

import com.rtc.app.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAuthService {
    private final UserRepository repository;

}
