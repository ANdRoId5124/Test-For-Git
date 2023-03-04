package com.example.testsfordto.ValidationTest;


import com.example.testsfordto.Dto.RegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegistrationRequestValidationTest {

    private Validator validator;

    private static Stream<Arguments> provideSomeStrings() {
        return Stream.of(
                Arguments.of("Андрей", false)
        );
    }

    @BeforeEach
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@yandex.ru", "test@gmail.com", "snow123@mail.ru",
            "patriarch.kirill@pravoslavnaya.cerkov.ru", "joe-bye@gmail.com",
            "ala_123@gmail.com"})
    void shouldBeTrueForEmail(String email) {
        final RegistrationRequest DTO = getDto("Andrei", "Lacoste",
                email, "Tykwa01:");
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Алексей", "Андрей", "Alex", "Jan", "OLIVER", "андрей", "aleksei"})
    void shouldBeTrueForName(String name) {
        final RegistrationRequest DTO = getDto(name, "Lacoste",
                "test@gmail.com", "Tykva01:");
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        assertTrue(violations.isEmpty());

    }

    @ParameterizedTest
    @ValueSource(strings = {"IVANOV", "ivanov", "Ivanov", "Иванов", "ИВАНОВ", "иванов"})
    void shouldBeTrueForLastName(String lastName) {
        final RegistrationRequest DTO = getDto("Andrei", lastName,
                "test@gmail.com", "Tykva01:");
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"tYkwa1!", "PqTf1@", "orF!@#2"})
    void shouldReturnTrueForPassword(String password) {
        final RegistrationRequest DTO = getDto("Andrew", "Lacoste",
                "test@gmail.com", password);
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t\n\r", "a", "A"})
    void shouldReturnErrorMessageWhenNameInvalid(String name) {
        final RegistrationRequest DTO = getDto(name, "Lacoste",
                "test@gmail.com", "Tykva01:");
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("First name is not valid"));
    }


    @ParameterizedTest
    @ValueSource(strings = {" ", "\t\n\r", "a", "A"})
    void shouldReturnErrorMessageWhenLastNameIvalid(String lastName) {
        final RegistrationRequest DTO = getDto("Andrei", lastName,
                "test@gmail.com", "Tykva01:");
        DTO.setPassword("Tykva01:");
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Last name is not valid"));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t\n\r", "test.@gmail.com", ".user.name@gmail.com",
            "test-name@gmail.com.", "ala@.com", ""})
    void shouldReturnErrorMessageWhenEmailInvalid(String email) {
        final RegistrationRequest DTO = getDto("Andrew", "Lacoste",
                email, "Tykva01:");
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Email is not valid"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdg1", "abcdG1", "Abc!1", "abcdert", " ", "\t\n\r"})
    void shouldReturnErrorMessageWhenPasswordInvalid(String password) {
        final RegistrationRequest DTO = getDto("Andrew", "Lacoste",
                "test@gmail.com", password);
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Password is not valid"));
    }

    @Test
    void shouldReturnErrorWhenEmailIsNull() {
        final RegistrationRequest DTO = getDto("Andrew", "Lacoste",
                null, "Tykva01:");
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Email is should not be null"));
    }

    @Test
    void shouldReturnErrorWhenNameIsNull() {
        final RegistrationRequest DTO = getDto(null, "Lacoste",
                "test@gmail.com", "Tykva01:");
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("First name is should not be null"));
    }

    @Test
    void shouldReturnErrorWhenLastNameIsNull() {
        final RegistrationRequest DTO = getDto("Andrew", null,
                "test@gmail.com", "Tykva01:");
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Last name is should not be null"));
    }

    @Test
    void shouldReturnErrorWhenPasswordIsNull() {
        final RegistrationRequest DTO = getDto("Andrew", "Lacoste",
                "test@gmail.com", null);
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(DTO);
        String errorMessage = violations.iterator().next().getMessage();
        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Password is should not be null"));
    }

    private RegistrationRequest getDto(String name, String lastName,
                                       String email, String password) {
        RegistrationRequest dto = new RegistrationRequest();
        dto.setFirstName(name);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setPassword(password);
        return dto;
    }

}
