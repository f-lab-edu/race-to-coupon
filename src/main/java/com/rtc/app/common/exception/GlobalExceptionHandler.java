package com.rtc.app.common.exception;

import com.rtc.app.auth.exception.DuplicationException;
import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.exception.dto.ErrorResponse;
import com.rtc.app.common.response.ApiCommonResponseCode;
import com.rtc.app.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleDuplicationException(DuplicationException exception) {
        return new ResponseEntity<>(ApiResponse.error(ApiAuthResponseCode.DUPLICATE_FILED, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 전체 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleMethodArgumentNotValidException(Exception exception) {
        return new ResponseEntity<>(ApiResponse.error(ApiCommonResponseCode.INTERNAL_ERROR, "error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
