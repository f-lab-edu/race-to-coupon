package com.rtc.app.coupon.entity;

import com.rtc.app.coupon.type.DiscountType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class DiscountPolicy {

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false, length = 10, updatable = false)
    private DiscountType discountType;

    @Column(name = "discount_value", nullable = false)
    private Integer discountValue;

    /**
     * 쿠폰 적용 가능 최소 금액
     * 0인 경우 금액 제한 없이 사용 가능
     */
    @Column(name = "min_apply_amount", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer minApplyAmount;

    /**
     * 쿠폰 적용 가능 최대 금액
     * 0인 경우 금액 제한 없이 사용 가능
     */
    @Column(name = "max_apply_amount", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer maxApplyAmount;

    private DiscountPolicy(DiscountType discountType, Integer discountValue,
                           Integer minApplyAmount, Integer maxApplyAmount) {

        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minApplyAmount = minApplyAmount;
        this.maxApplyAmount = maxApplyAmount;
    }

    public static DiscountPolicy create(DiscountType discountType, Integer discountValue,
                                        Integer minApplyAmount, Integer maxApplyAmount) {

        return new DiscountPolicy(discountType, discountValue, minApplyAmount, maxApplyAmount);
    }
}
