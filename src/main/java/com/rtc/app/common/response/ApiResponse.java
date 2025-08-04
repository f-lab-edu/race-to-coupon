package com.rtc.app.common.response;

import com.rtc.app.common.exception.dto.ErrorResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ApiResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    private ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(ResponseCode code, T data) {
        return new ApiResponse<>(code.getCode(), code.getMessage(), data);
    }

    public static ApiResponse<ErrorResponse> error(ResponseCode code, String message) {
        return new ApiResponse<>(code.getCode(), code.getMessage(), new ErrorResponse(message));
    }

    // 파라미터 검증 에러를 위한 메서드
    public static ApiResponse<Map<String, String>> error(ResponseCode code, Map<String, String> parameters) {
        return new ApiResponse<>(code.getCode(), code.getMessage(), parameters);
    }
}
