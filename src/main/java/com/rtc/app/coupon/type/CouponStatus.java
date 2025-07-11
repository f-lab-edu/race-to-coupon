package com.rtc.app.coupon.type;

import lombok.Getter;

@Getter
public enum CouponStatus {
    ACTIVATED("활성화"),
    DELETED("삭제");

    private final String description;

    CouponStatus(String description) {
        this.description = description;
    }
}