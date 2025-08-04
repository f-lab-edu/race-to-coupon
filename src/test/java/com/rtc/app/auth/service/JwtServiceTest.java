package com.rtc.app.auth.service;

import com.rtc.app.auth.service.authentication.JwtService;
import com.rtc.app.auth.type.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JwtService 테스트")
class JwtServiceTest {

    private final String secret = "secretsecretsecretsecretsecretse";
    private final long expirationMs = 3_600_000;
    private final long refreshExpirationMs = 7_200_000;
    private final JwtService jwtService = new JwtService(secret, expirationMs, refreshExpirationMs);

    @ParameterizedTest(name = "{index} - input id = {0}")
    @NullSource
    @DisplayName("액세스 토큰 생성시 id가 null이면 IllegalArgumentException 던짐")
    void shouldThrowIllegalArgumentExceptionWhenGenerateAccessTokenIdIsNullOrBlank(Long id) {
        // given
        String email = "email@email.com";
        String type = UserType.USER.name();

        // when, then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> jwtService.generateAccessToken(id, email, type));

        assertEquals("ID는 필수 값입니다.", exception.getMessage());
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @ParameterizedTest(name = "{index} - input id = {0}")
    @NullAndEmptySource
    @DisplayName("액세스 토큰 생성시 email이 null 또는 공백이면 IllegalArgumentException 던짐")
    void shouldThrowIllgalArgumentExceptionWhenGenerateAccessTokenEmailIsNullOrBlank(String email) {
        // given
        Long id = 1L;
        String type = UserType.USER.name();

        // when, then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> jwtService.generateAccessToken(id, email, type));

        assertEquals("이메일은 필수 값입니다.", exception.getMessage());
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("액세스 토큰 생성시 type(유저)이 null 또는 공백이면 IllegalArgumentException 던짐")
    void shouldThrowIllgalArgumentExceptionWhenGenerateAccessTokenTypeIsNull(String type) {
        // given
        Long id = 1L;
        String email = "email@email.com";

        // when, then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> jwtService.generateAccessToken(id, email, type));

        assertEquals("유저 타입은 필수 값입니다.", exception.getMessage());
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }
}