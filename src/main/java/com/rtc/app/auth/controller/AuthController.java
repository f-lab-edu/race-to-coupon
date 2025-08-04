package com.rtc.app.auth.controller;

import com.rtc.app.auth.dto.internal.CustomUserDetails;
import com.rtc.app.auth.dto.request.SignUpRequest;
import com.rtc.app.auth.dto.request.command.SignUpCommand;
import com.rtc.app.auth.dto.response.SignUpResponse;
import com.rtc.app.auth.service.AuthService;
import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인 요청 처리는 이 컨트롤러에서 직접 수행하지 않습니다.
 * 실제 인증 로직은 {@link com.rtc.app.auth.filter.EmailPasswordAuthenticationFilter} 에서 처리됩니다.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponse>> signup(@Valid @RequestBody SignUpRequest request) {
        SignUpResponse response = authService.signUp(SignUpCommand.from(request));
        return new ResponseEntity<>(ApiResponse.of(ApiAuthResponseCode.SIGNUP_SUCCESS, response), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@AuthenticationPrincipal CustomUserDetails user) {
        authService.logout(user.getId());
        return new ResponseEntity<>(ApiResponse.of(ApiAuthResponseCode.LOGOUT_SUCCESS, null), HttpStatus.OK);
    }
}