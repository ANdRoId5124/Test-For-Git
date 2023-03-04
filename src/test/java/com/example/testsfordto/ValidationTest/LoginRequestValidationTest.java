package com.example.testsfordto.ValidationTest;


import com.example.testsfordto.Dto.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginRequestValidationTest {
    private Validator validator;


    @BeforeEach
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t\n\r", "test.@gmail.com", ".user.name@gmail.com",
            "test-name@gmail.com.", "ala@.com"})
    void shouldReturnErrorMessageWhenEmailNotValid(String email) {
        final LoginRequest DTO = getDto(email, "Tykva01:");
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Email is not valid"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdg1", "abcdG1", "Abc!1", "abcdert", " ", "\t\n\r"})
    void shouldReturnErrorMessageWhenPasswordNotValid(String password) {
        final LoginRequest DTO = getDto("test@gmail.com", password);
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Password is not valid"));
    }

    @Test
    void shouldReturnErrorMessageWhenEmailNull() {
        final LoginRequest DTO = getDto(null, "Tykva1:");
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Email should not be null"));
    }

    @Test
    void shouldReturnErrorMessageWhenPasswordNull() {
        final LoginRequest DTO = getDto("test@gmail.com", null);
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Password should not be null"));
    }

    private LoginRequest getDto(String email, String password) {
        LoginRequest dto = new LoginRequest();
        dto.setEmail(email);
        dto.setPassword(password);
        return dto;
    }
}
