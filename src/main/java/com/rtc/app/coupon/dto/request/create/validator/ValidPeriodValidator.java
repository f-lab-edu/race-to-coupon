package com.rtc.app.coupon.dto.request.create.validator;

import com.rtc.app.coupon.dto.request.create.ValidPeriodInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPeriodValidator implements ConstraintValidator<ValidPeriod, ValidPeriodInfo> {

    @Override
    public boolean isValid(ValidPeriodInfo value, ConstraintValidatorContext context) {
        if (value == null) return true;
        if (value.getDownloadStart() == null || value.getDownloadEnd() == null) return true;

        return value.getDownloadStart().isBefore(value.getDownloadEnd());
    }
}