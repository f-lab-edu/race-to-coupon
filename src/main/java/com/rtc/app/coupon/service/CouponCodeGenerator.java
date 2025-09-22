package com.rtc.app.coupon.service;

import com.rtc.app.coupon.exception.CouponCodeDuplicateException;
import com.rtc.app.coupon.repository.CouponInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class CouponCodeGenerator {

    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 10;
    private static final int MAX_RETRY = 3;

    private final CouponInfoRepository repository;

    public String generateCouponCode() {
        for (int i = 0; i < MAX_RETRY; i++) {
            String code = generateRandomCode();
            if (!repository.existsByCode(code)) {
                return code;
            }
        }

        throw new CouponCodeDuplicateException("쿠폰 코드 생성 실패: 중복 코드 발생");
    }

    private String generateRandomCode() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }
        return sb.toString();
    }
}
