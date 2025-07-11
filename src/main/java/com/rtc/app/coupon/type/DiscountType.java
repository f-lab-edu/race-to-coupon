package com.rtc.app.coupon.type;

import lombok.Getter;

@Getter
public enum DiscountType {
    AMOUNT("정액"),
    RATE("정률");

    private final String description;

    DiscountType(String description) {
        this.description = description;
    }
}

