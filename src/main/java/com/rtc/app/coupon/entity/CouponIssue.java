package com.rtc.app.coupon.entity;

import com.rtc.app.common.entity.BaseTimeEntity;
import com.rtc.app.coupon.type.CouponIssueStatus;
import com.rtc.app.account.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "coupon_issue")
public class CouponIssue extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_issue_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_info_id", nullable = false)
    private CouponInfo couponInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private CouponIssueStatus status;

    /**
     * 쿠폰 사용 가능 시작 시간
     */
    @Column(name = "usable_start", nullable = false)
    private LocalDateTime usableStart;

    /**
     * 쿠폰 사용 가능 종료 시간
     */
    @Column(name = "usable_end", nullable = false)
    private LocalDateTime usableEnd;
}