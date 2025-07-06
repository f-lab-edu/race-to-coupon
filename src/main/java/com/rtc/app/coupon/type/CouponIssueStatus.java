package com.rtc.app.coupon.type;

import lombok.Getter;

@Getter
public enum CouponIssueStatus {
    ISSUED("발급"),
    USED("사용"),
    EXPIRED("만료");

    private final String description;

    CouponIssueStatus(String description) {
        this.description = description;
    }
}
