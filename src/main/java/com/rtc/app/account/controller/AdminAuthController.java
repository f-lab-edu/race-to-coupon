package com.rtc.app.account.controller;

import com.rtc.app.account.service.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/auth")
public class AdminAuthController {
    private final AdminAuthService adminAuthService;

}
