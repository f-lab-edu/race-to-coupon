package com.rtc.app.auth.exception;

import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.response.ResponseCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private final ResponseCode code = ApiAuthResponseCode.AUTHENTICATION_FAILED;

    public JwtAuthenticationException(String message) {
        super(message);
    }
}
