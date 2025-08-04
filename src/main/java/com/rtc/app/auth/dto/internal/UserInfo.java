package com.rtc.app.auth.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfo {

    private final Long id;
    private final String email;
    private final String type;
}
