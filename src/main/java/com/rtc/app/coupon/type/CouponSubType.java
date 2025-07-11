package com.rtc.app.coupon.type;

import lombok.Getter;

@Getter
public enum CouponSubType {
    FIRST("선착순"),
    SUMMER_EVENT("여름맞이 행사");

    private final String description;

    CouponSubType(String description) {
        this.description = description;
    }
}

