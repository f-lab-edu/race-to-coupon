package com.rtc.app.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponse {

    private final String accessToken;
    private final String refreshToken;
}
