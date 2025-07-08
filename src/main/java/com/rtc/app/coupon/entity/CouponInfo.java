package com.rtc.app.coupon.entity;

import com.rtc.app.common.entity.BaseTimeEntity;
import com.rtc.app.coupon.type.CouponStatus;
import com.rtc.app.coupon.type.CouponSubType;
import com.rtc.app.coupon.type.CouponType;
import com.rtc.app.coupon.type.DiscountType;
import com.rtc.app.account.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "coupon_info")
public class CouponInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_user_id", updatable = false, nullable = false)
    private User createUser;

    /**
     * 쿠폰 입력 코드
     */
    @Column(name = "code", unique = true, length = 10)
    private String code;

    @Column(name = "title", nullable = false, length = 90)
    private String title;

    @Column(name = "description", length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private CouponStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private CouponType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "sub_type", nullable = false, length = 20)
    private CouponSubType subType;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false, length = 10, updatable = false)
    private DiscountType discountType;

    @Column(name = "discount_value", nullable = false, updatable = false)
    private Integer discountValue;

    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name = "issued_quantity", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer issuedQuantity;

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

    @Column(name = "validate_days_after_download", nullable = false, updatable = false)
    private Integer validateDaysAfterDownload;

    @Column(name = "download_start", nullable = false)
    private LocalDateTime downloadStart;

    @Column(name = "download_end", nullable = false)
    private LocalDateTime downloadEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modify_user_id")
    private User modifyUser;
}