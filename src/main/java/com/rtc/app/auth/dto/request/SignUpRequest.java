package com.rtc.app.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{1,10}$", message = "한글,영어,숫자 1~10자리를 입력해주세요")
    private final String name;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*]).{8,20}$",
            message = "영문 소문자, 특수문자(!@#$%^&*), 숫자를 포함한 8~20자리를 입력해주세요"
    )
    private final String password;

    @JsonCreator
    public SignUpRequest(@JsonProperty("email") String email,
                         @JsonProperty("name") String name,
                         @JsonProperty("password") String password) {

        this.email = email;
        this.name = name;
        this.password = password;
    }
}
