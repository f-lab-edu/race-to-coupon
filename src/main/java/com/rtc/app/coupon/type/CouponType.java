package com.rtc.app.coupon.type;

import lombok.Getter;

@Getter
public enum CouponType {
    EVENT("이벤트");

    private final String description;

    CouponType(String description) {
        this.description = description;
    }
}

