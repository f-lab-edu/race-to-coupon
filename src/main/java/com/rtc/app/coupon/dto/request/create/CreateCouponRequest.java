package com.rtc.app.coupon.dto.request.create;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rtc.app.coupon.type.CouponSubType;
import com.rtc.app.coupon.type.CouponType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCouponRequest {

    @NotNull private final String title;
    @NotNull private final String description;
    @NotNull private final CouponType type;
    @NotNull private final CouponSubType subType;
    @NotNull private final Integer totalQuantity;

    @NotNull @Valid private final DiscountInfo discountInfo;
    @NotNull @Valid private final ValidPeriodInfo validPeriod;

    @JsonCreator
    public CreateCouponRequest(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("type") CouponType type,
            @JsonProperty("sub_type") CouponSubType subType,
            @JsonProperty("total_quantity") Integer totalQuantity,
            @JsonProperty("discount_info") DiscountInfo discountInfo,
            @JsonProperty("valid_period") ValidPeriodInfo validPeriod) {

        this.title = title;
        this.description = description;
        this.type = type;
        this.subType = subType;
        this.totalQuantity = totalQuantity;
        this.discountInfo = discountInfo;
        this.validPeriod = validPeriod;
    }
}
