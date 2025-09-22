package com.rtc.app.coupon.dto.request.create;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rtc.app.coupon.dto.request.create.validator.ValidDiscountAmount;
import com.rtc.app.coupon.type.DiscountType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@ValidDiscountAmount
public class DiscountInfo {

    @NotNull private final DiscountType discountType;
    @NotNull private final Integer discountValue;
    @NotNull private final Integer minApplyAmount;
    @NotNull private final Integer maxApplyAmount;

    @JsonCreator
    public DiscountInfo(
            @JsonProperty("discount_type") DiscountType discountType,
            @JsonProperty("discount_value") Integer discountValue,
            @JsonProperty("min_apply_amount") Integer minApplyAmount,
            @JsonProperty("max_apply_amount") Integer maxApplyAmount
    ) {
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minApplyAmount = minApplyAmount;
        this.maxApplyAmount = maxApplyAmount;
    }
}
