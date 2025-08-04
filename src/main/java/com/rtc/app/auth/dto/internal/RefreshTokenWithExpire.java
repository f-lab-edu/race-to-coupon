package com.rtc.app.auth.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RefreshTokenWithExpire {
    private final String refreshToken;
    private Date expireAt;
}
