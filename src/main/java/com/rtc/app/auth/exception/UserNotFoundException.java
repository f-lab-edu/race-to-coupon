package com.rtc.app.auth.exception;

import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.response.ResponseCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

  private final ResponseCode code = ApiAuthResponseCode.USER_NOT_FOUND;

    public UserNotFoundException(String message) {
        super(message);
    }
}
