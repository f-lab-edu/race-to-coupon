package com.rtc.app.coupon.type;

import com.rtc.app.common.response.ResponseCode;

public enum ApiCouponResponseCode implements ResponseCode {
    COUPON_CREATED_SUCCESS(20020000, "COUPON_CREATED_SUCCESS"),
    COUPON_NOT_FOUND(40020000, "COUPON_NOT_FOUND"),
    COUPON_CODE_DUPLICATE_EXCEPTION(50020000, "COUPON_CODE_DUPLICATE_EXCEPTION");

    private final int code;
    private final String message;

    ApiCouponResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() { return code; }

    @Override
    public String getMessage() { return message; }
}
