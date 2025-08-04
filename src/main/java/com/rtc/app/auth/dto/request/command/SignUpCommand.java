package com.rtc.app.auth.dto.request.command;

import com.rtc.app.auth.dto.request.SignUpRequest;
import lombok.Getter;

@Getter
public class SignUpCommand {

    private final String email;
    private final String name;
    private final String password;

    private SignUpCommand(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static SignUpCommand from(SignUpRequest request) {
        return new SignUpCommand(request.getEmail(), request.getName(), request.getPassword());
    }
}
