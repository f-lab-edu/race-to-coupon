package com.rtc.app.coupon.dto.request.create.command;

import com.rtc.app.coupon.dto.request.create.CreateCouponRequest;
import com.rtc.app.coupon.dto.request.create.DiscountInfo;
import com.rtc.app.coupon.dto.request.create.ValidPeriodInfo;
import com.rtc.app.coupon.type.CouponSubType;
import com.rtc.app.coupon.type.CouponType;
import com.rtc.app.coupon.type.DiscountType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCouponCommand {

    private final String title;
    private final String description;
    private final CouponType type;
    private final CouponSubType subType;
    private final Integer totalQuantity;

    private final DiscountType discountType;
    private final Integer discountValue;
    private final Integer minApplyAmount;
    private final Integer maxApplyAmount;

    private final Integer validateDays;
    private final LocalDateTime downloadStart;
    private final LocalDateTime downloadEnd;

    private CreateCouponCommand(String title, String description, CouponType type, CouponSubType subType,
                                Integer totalQuantity, DiscountType discountType, Integer discountValue,
                                Integer minApplyAmount, Integer maxApplyAmount, Integer validateDays,
                                LocalDateTime downloadStart, LocalDateTime downloadEnd) {

        this.title = title;
        this.description = description;
        this.type = type;
        this.subType = subType;
        this.totalQuantity = totalQuantity;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minApplyAmount = minApplyAmount;
        this.maxApplyAmount = maxApplyAmount;
        this.validateDays = validateDays;
        this.downloadStart = downloadStart;
        this.downloadEnd = downloadEnd;
    }

    public static CreateCouponCommand from(CreateCouponRequest request) {
        DiscountInfo discount = request.getDiscountInfo();
        ValidPeriodInfo period = request.getValidPeriod();

        return new CreateCouponCommand(
                request.getTitle(),
                request.getDescription(),
                request.getType(),
                request.getSubType(),
                request.getTotalQuantity(),
                discount.getDiscountType(),
                discount.getDiscountValue(),
                discount.getMinApplyAmount(),
                discount.getMaxApplyAmount(),
                period.getValidateDays(),
                period.getDownloadStart(),
                period.getDownloadEnd()
        );
    }
}