package com.rtc.app.coupon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AvailablePeriod {

    @Column(name = "download_start", nullable = false)
    private LocalDateTime downloadStart;

    @Column(name = "download_end", nullable = false)
    private LocalDateTime downloadEnd;

    @Column(name = "validate_days_after_download", nullable = false, updatable = false)
    private Integer validateDaysAfterDownload;

    private AvailablePeriod(LocalDateTime downloadStart, LocalDateTime downloadEnd, Integer validateDays) {
        this.downloadStart = downloadStart;
        this.downloadEnd = downloadEnd;
        this.validateDaysAfterDownload = validateDays;
    }

    public static AvailablePeriod create(LocalDateTime downloadStart, LocalDateTime downloadEnd, Integer validateDays) {
        return new AvailablePeriod(downloadStart, downloadEnd, validateDays);
    }
}
