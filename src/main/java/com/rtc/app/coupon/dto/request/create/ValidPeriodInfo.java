package com.rtc.app.coupon.dto.request.create;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rtc.app.coupon.dto.request.create.validator.ValidPeriod;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@ValidPeriod
public class ValidPeriodInfo {

    @NotNull private final Integer validateDays;
    @NotNull private final LocalDateTime downloadStart;
    @NotNull private final LocalDateTime downloadEnd;

    @JsonCreator
    public ValidPeriodInfo(
            @JsonProperty("validate_days") Integer validateDays,
            @JsonProperty("download_start") LocalDateTime downloadStart,
            @JsonProperty("download_end") LocalDateTime downloadEnd) {

        this.validateDays = validateDays;
        this.downloadStart = downloadStart;
        this.downloadEnd = downloadEnd;
    }
}
