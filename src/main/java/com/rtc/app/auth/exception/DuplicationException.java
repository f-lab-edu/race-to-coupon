package com.rtc.app.auth.exception;

import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.response.ResponseCode;
import org.springframework.security.core.AuthenticationException;

public class DuplicationException extends AuthenticationException {

    private final ResponseCode code = ApiAuthResponseCode.DUPLICATE_FILED;

    public DuplicationException(String message) {
        super(message);
    }
}
