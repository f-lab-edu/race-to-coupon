package com.rtc.app.auth.exception;

import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.response.ResponseCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtExpirationException extends AuthenticationException {

    private final ResponseCode code = ApiAuthResponseCode.JWT_EXPIRED;

    public JwtExpirationException(String message) {
        super(message);
    }
}
