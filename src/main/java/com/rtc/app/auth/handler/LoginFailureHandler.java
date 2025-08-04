package com.rtc.app.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        // TODO: 실패 로그 나중에 찍어야 함

        String jsonLoginResponse = objectMapper.writeValueAsString(ApiResponse.error(ApiAuthResponseCode.LOGIN_FAIL, "로그인에 실패하였습니다."));
        response.getWriter().write(jsonLoginResponse);
    }
}
