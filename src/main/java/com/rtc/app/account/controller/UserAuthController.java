package com.rtc.app.account.controller;

import com.rtc.app.account.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/auth")
public class UserAuthController {
    private final UserAuthService userAuthService;

}
