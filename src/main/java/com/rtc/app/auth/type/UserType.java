package com.rtc.app.auth.type;

import lombok.Getter;

@Getter
public enum UserType {
    USER("유저", "ROLE_USER"),
    ADMIN("운영자", "ROLE_ADMIN"),
    PARTNER("파트너", "ROLE_PARTNER");

    private final String description;
    private final String securityLevel;

    UserType(String description, String securityLevel) {
        this.description = description;
        this.securityLevel = securityLevel;
    }

}

