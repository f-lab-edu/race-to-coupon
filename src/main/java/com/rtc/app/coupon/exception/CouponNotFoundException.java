package com.rtc.app.coupon.exception;

import com.rtc.app.common.response.ResponseCode;
import com.rtc.app.coupon.type.ApiCouponResponseCode;
import lombok.Getter;

@Getter
public class CouponNotFoundException extends RuntimeException {

    private final ResponseCode code = ApiCouponResponseCode.COUPON_NOT_FOUND;

    public CouponNotFoundException(String message) {
        super(message);
    }
}
