package com.rtc.app.coupon.controller;

import com.rtc.app.coupon.service.AdminCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coupons")
public class AdminCouponController {
    private final AdminCouponService adminCouponService;

}
