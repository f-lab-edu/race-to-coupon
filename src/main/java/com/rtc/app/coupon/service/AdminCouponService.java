package com.rtc.app.coupon.service;

import com.rtc.app.coupon.repository.CouponInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminCouponService {
    private final CouponInfoRepository repository;

}
