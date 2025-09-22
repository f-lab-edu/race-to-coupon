package com.rtc.app.coupon.dto.request.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rtc.app.coupon.type.CouponStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCouponRequest {

    private final String title;
    private final String description;
    private final LocalDateTime downloadStartDate;
    private final LocalDateTime downloadEndDate;
    private final CouponStatus status;

    @JsonCreator
    public UpdateCouponRequest(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("download_start_date") LocalDateTime downloadStartDate,
            @JsonProperty("download_end_date") LocalDateTime downloadEndDate,
            @JsonProperty("status") CouponStatus status) {

        this.title = title;
        this.description = description;
        this.downloadStartDate = downloadStartDate;
        this.downloadEndDate = downloadEndDate;
        this.status = status;
    }
}
