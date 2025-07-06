package com.rtc.app.coupon.controller;

import com.rtc.app.coupon.service.UserCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/coupons")
public class UserCouponController {
    private final UserCouponService userCouponService;

}
