package com.rtc.app.account.type;

import lombok.Getter;

@Getter
public enum UserType {
    USER("유저"),
    ADMIN("운영자"),
    PARTNER("파트너");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

}

