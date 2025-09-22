package com.rtc.app.coupon.dto.request.create.validator;

import com.rtc.app.coupon.dto.request.create.DiscountInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDiscountAmountValidator implements ConstraintValidator<ValidDiscountAmount, DiscountInfo> {

    @Override
    public boolean isValid(DiscountInfo value, ConstraintValidatorContext context) {
        if (value == null) return true;

        Integer min = value.getMinApplyAmount();
        Integer max = value.getMaxApplyAmount();

        if (min == null || max == null) return true;

        if (min == 0 && max == 0) return true;

        return min < max;
    }
}