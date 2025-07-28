package com.rtc.app.common.response;

public enum ApiCommonResponseCode implements ResponseCode {
    INVALID_PARAMETERS(4000005, "INVALID_PARAMETERS"),
    INTERNAL_ERROR(5000000, "INTERNAL_ERROR");

    private final int code;
    private final String message;

    ApiCommonResponseCode(int code, String message) {
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
