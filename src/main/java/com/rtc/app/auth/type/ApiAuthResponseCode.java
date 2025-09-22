package com.rtc.app.auth.type;

import com.rtc.app.common.response.ResponseCode;

public enum ApiAuthResponseCode implements ResponseCode {
    LOGIN_FAIL(4001000, "LOGIN FAIL"),
    JWT_EXPIRED(40010002, "JWT_EXPIRED"),
    AUTHENTICATION_FAILED(40010003, "AUTHENTICATION_FAILED"),
    MISSING_JWT(40010004, "MISSING_JWT"),
    USER_NOT_FOUND(40010005, "USER_NOT_FOUND"),
    LOGIN_SUCCESS(20010001, "LOGIN_SUCCESS"),
    LOGOUT_SUCCESS(20010002, "LOGOUT_SUCCESS"),
    SIGNUP_SUCCESS(20010003, "SIGNUP_SUCCESS"),
    DUPLICATE_FILED(20010004, "DUPLICATE_FIELD");

    private final int code;
    private final String message;

    ApiAuthResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
