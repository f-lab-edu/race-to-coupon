package com.rtc.app.auth.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SignUpRequest 테스트")
public class SignUpRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "{index} - input email = {0}")
    @NullAndEmptySource
    @DisplayName("이메일에 공백 또는 null이 들어올 경우 검증 실패")
    void shouldValidationFailWhenEmailIsBlankOrNull(String email) {
        // given
        String name = "nickName";
        String password = "passwWord123!";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("email")))
                .isTrue();
    }

    @ParameterizedTest(name = "{index} - input nickname = {0}")
    @ValueSource(strings = {"invalid", "email@"})
    @DisplayName("이메일 형식이 유효하지 않을 경우 검증 실패")
    void shouldFailValidationWhenEmailFormatIsInvalid(String email) {
        // given
        String name = "nickName";
        String password = "passwWord123!";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("email")))
                .isTrue();
    }

    @ParameterizedTest(name = "{index} - input nickname = {0}")
    @ValueSource(strings = {"email@email.com", "abc.c@email.com"})
    @DisplayName("이메일 형식이 올바른 경우 유효성 검사 성공")
    void shouldPassValidationWhenEmailFormatIsValid(String email) {
        // given
        String name = "nickName";
        String password = "passwWord123!";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isEmpty();
    }

    @ParameterizedTest(name = "{index} - input name = {0}")
    @NullAndEmptySource
    @DisplayName("이름에 공백 또는 null이 들어올 경우 검증 실패")
    void shouldValidationFailWhenNameIsBlankOrNull(String name) {
        // given
        String email = "email@email.com";
        String password = "passwWord123!";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("name")))
                .isTrue();
    }

    @ParameterizedTest(name = "{index} - input nickname = {0}")
    @ValueSource(strings = {"안녕!", "안녕★"})
    @DisplayName("이름 형식이 유효하지 않을 경우 검증 실패")
    void shouldFailValidationWhenNameFormatIsInvalid(String name) {
        // given
        String email = "email@email.com";
        String password = "passwWord123!";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("name")))
                .isTrue();
    }

    @ParameterizedTest(name = "{index} - input nickname = {0}")
    @ValueSource(strings = {"안녕안녕안녕안녕안녕안", "bcdefg12345"})
    @DisplayName("이름 길이가 유효하지 않을 경우 검증 실패")
    void shouldFailValidationWhenNameLengthIsInvalid(String name) {
        // given
        String email = "email@email.com";
        String password = "passwWord123!";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("name")))
                .isTrue();
    }

    @ParameterizedTest(name = "{index} - input nickname = {0}")
    @ValueSource(strings = {"안녕하세요", "반갑습니다"})
    @DisplayName("이름이 유효할 경우 검증 성공")
    void shouldPassValidationWhenNameFormatIsValid(String name) {
        // given
        String email = "email@email.com";
        String password = "passwWord123!";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isEmpty();
    }

    @ParameterizedTest(name = "{index} - input name = {0}")
    @NullAndEmptySource
    @DisplayName("패스워드에 공백 또는 null이 들어올 경우 검증 실패")
    void shouldValidationFailWhenPasswordIsBlankOrNull(String password) {
        // given
        String email = "email@email.com";
        String name = "이름";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("password")))
                .isTrue();
    }

    @ParameterizedTest(name = "{index} - input nickname = {0}")
    @ValueSource(strings = {"dkssudgktpdy2!zaefda1", "dmdkr%2"})
    @DisplayName("패스워드 길이가 유효하지 않을 경우 검증 실패")
    void shouldFailValidationWhenPasswordLengthIsInvalid(String password) {
        // given
        String email = "email@email.com";
        String name = "이름";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("password")))
                .isTrue();
    }

    @ParameterizedTest(name = "{index} - input nickname = {0}")
    @ValueSource(strings = {"dkssudgktpdy"})
    @DisplayName("패스워드 형식 유효하지 않을 경우 검증 실패")
    void shouldFailValidationWhenPasswordFormatIsInvalid(String password) {
        // given
        String email = "email@email.com";
        String name = "이름";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("password")))
                .isTrue();
    }

    @ParameterizedTest(name = "{index} - input nickname = {0}")
    @ValueSource(strings = {"pass1ord!", "helloHi$4"})
    @DisplayName("패스워드 형식이 올바른 경우 유효성 검사 성공")
    void shouldPassValidationWhenPasswordFormatIsValid(String password) {
        // given
        String email = "email@email.com";
        String name = "안녕하세요";
        SignUpRequest request = new SignUpRequest(email, name, password);

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        // then
        assertThat(violations).isEmpty();
    }
}
