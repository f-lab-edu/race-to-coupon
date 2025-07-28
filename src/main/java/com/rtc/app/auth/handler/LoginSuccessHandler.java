package com.rtc.app.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtc.app.auth.dto.response.LoginResponse;
import com.rtc.app.auth.dto.internal.CustomUserDetails;
import com.rtc.app.auth.service.authentication.AuthTokenService;
import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.response.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final AuthTokenService authTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                        Authentication authentication) throws IOException, ServletException {

        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
        // 각 회원은 하나의 역할만 존재
        String role = userDetail.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
        LoginResponse loginResponse = authTokenService.issueTokens(userDetail.getId(), userDetail.getEmail(), role);

        String jsonLoginResponse = objectMapper.writeValueAsString(ApiResponse.of(ApiAuthResponseCode.LOGOUT_SUCCESS, loginResponse));
        response.getWriter().write(jsonLoginResponse);
    }
}
