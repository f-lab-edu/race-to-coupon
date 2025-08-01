package com.rtc.app.auth.exception;

import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.response.ResponseCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class MissingJwtException extends AuthenticationException {

    private final ResponseCode code = ApiAuthResponseCode.MISSING_JWT;

    public MissingJwtException(String message) {
        super(message);
    }
}
