package com.rtc.app.common.exception;

import com.rtc.app.auth.exception.DuplicateException;
import com.rtc.app.auth.exception.UserNotFoundException;
import com.rtc.app.common.exception.dto.ErrorResponse;
import com.rtc.app.common.response.ApiCommonResponseCode;
import com.rtc.app.common.response.ApiResponse;
import com.rtc.app.coupon.exception.CouponCodeDuplicateException;
import com.rtc.app.coupon.exception.CouponNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 파라미터 검증 오류
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(ApiResponse.error(ApiCommonResponseCode.INVALID_PARAMETERS, errors), HttpStatus.BAD_REQUEST);
    }

    // 값 중복 에러(이메일, 닉네임 등)
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleDuplicationException(DuplicateException exception) {
        return new ResponseEntity<>(ApiResponse.error(exception.getCode(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 유저를 찾을 수 없음
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(ApiResponse.error(exception.getCode(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 쿠폰을 찾을 수 없음
    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleCouponNotFoundException(CouponNotFoundException exception) {
        return new ResponseEntity<>(ApiResponse.error(exception.getCode(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 쿠폰 코드 생성 실패
    @ExceptionHandler(CouponCodeDuplicateException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleUserNotFoundException(CouponCodeDuplicateException exception) {
        return new ResponseEntity<>(ApiResponse.error(exception.getCode(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 파싱 오류
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ApiCommonResponseCode response = ApiCommonResponseCode.NOT_READABLE_REQUEST;
        return new ResponseEntity<>(ApiResponse.error(response, response.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 전체 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleException(Exception exception) {
        ApiCommonResponseCode response = ApiCommonResponseCode.INTERNAL_ERROR;
        return new ResponseEntity<>(ApiResponse.error(response, response.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
