package com.rtc.app.coupon.controller;

import com.rtc.app.auth.dto.internal.CustomUserDetails;
import com.rtc.app.auth.dto.response.SignUpResponse;
import com.rtc.app.common.response.ApiCommonResponseCode;
import com.rtc.app.common.response.ApiResponse;
import com.rtc.app.coupon.dto.request.create.CreateCouponRequest;
import com.rtc.app.coupon.dto.request.create.command.CreateCouponCommand;
import com.rtc.app.coupon.dto.response.create.CreateCouponResponse;
import com.rtc.app.coupon.service.AdminCouponService;
import com.rtc.app.coupon.type.ApiCouponResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coupons")
public class AdminCouponController {
    private final AdminCouponService adminCouponService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<CreateCouponResponse>> create(@AuthenticationPrincipal CustomUserDetails admin,
                                                                    @Valid @RequestBody CreateCouponRequest request) {
        CreateCouponCommand command = CreateCouponCommand.from(request);

        // 쿠폰 생성
        Long couponId = adminCouponService.createCoupon(admin.getId(), command);

        CreateCouponResponse response = new CreateCouponResponse(couponId);

        return new ResponseEntity<>(ApiResponse.of(ApiCouponResponseCode.COUPON_CREATED_SUCCESS, response), HttpStatus.CREATED);
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<ApiResponse<Void>> delete(@AuthenticationPrincipal CustomUserDetails admin,
                                                    @PathVariable Long couponId) {

        adminCouponService.deleteCoupon(admin.getId(), couponId);
        return new ResponseEntity<>(ApiResponse.of(ApiCommonResponseCode.SUCCESS, null), HttpStatus.OK);
    }
}
