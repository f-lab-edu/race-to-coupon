package com.rtc.app.coupon.exception;

import com.rtc.app.common.response.ResponseCode;
import com.rtc.app.coupon.type.ApiCouponResponseCode;
import lombok.Getter;

@Getter
public class CouponCodeDuplicateException extends RuntimeException {

    private final ResponseCode code = ApiCouponResponseCode.COUPON_CODE_DUPLICATE_EXCEPTION;

    public CouponCodeDuplicateException(String message) {
        super(message);
    }
}
