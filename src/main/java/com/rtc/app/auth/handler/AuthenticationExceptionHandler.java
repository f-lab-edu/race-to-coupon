package com.rtc.app.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.exception.dto.ErrorResponse;
import com.rtc.app.common.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<ErrorResponse> errorResponse = ApiResponse.error(ApiAuthResponseCode.AUTHENTICATION_FAILED, "인증에 실패하였습니다.");

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
