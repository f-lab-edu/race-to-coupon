package com.rtc.app.coupon.dto.request.update.command;

import com.rtc.app.coupon.dto.request.update.UpdateCouponRequest;
import com.rtc.app.coupon.type.CouponStatus;
import com.rtc.app.coupon.type.CouponSubType;
import com.rtc.app.coupon.type.CouponType;
import com.rtc.app.coupon.type.DiscountType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCouponCommand {

    private final String title;
    private final String description;
    private final LocalDateTime downloadStartDate;
    private final LocalDateTime downloadEndDate;
    private final CouponStatus status;

    private UpdateCouponCommand(String title, String description,
                                LocalDateTime downloadStartDate, LocalDateTime downloadEndDate,
                                CouponStatus status) {

        this.title = title;
        this.description = description;
        this.downloadStartDate = downloadStartDate;
        this.downloadEndDate = downloadEndDate;
        this.status = status;
    }

    public static UpdateCouponCommand from(UpdateCouponRequest request) {
        return new UpdateCouponCommand(
                request.getTitle(),
                request.getDescription(),
                request.getDownloadStartDate(),
                request.getDownloadEndDate(),
                request.getStatus()
        );
    }
}