package com.rtc.app.coupon.repository;

import com.rtc.app.coupon.entity.CouponInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponInfoRepository extends JpaRepository<CouponInfo, Long> {

}