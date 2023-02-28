package com.pet.project.authorization.helper;

import com.pet.project.authorization.domain.entity.Roles;
import com.pet.project.authorization.domain.entity.User;
import com.pet.project.authorization.domain.request.RegistrationRequest;
import com.pet.project.authorization.domain.request.LoginRequest;
import com.pet.project.authorization.domain.response.UserInfoResponse;

import java.util.Set;

import static com.pet.project.authorization.domain.entity.ERole.ROLE_USER;

public class UserHelper {

  public static final String DEFAULT_PASSWORD = "password";
  public static final String ENCODE_PASSWORD =
      "$2a$10$r.ctB43XcviDPD0trfxdUeo7vag/N2JqnHGtXm7/sBmXjn567MEEm";

  private static final String JONNY_EMAIL = "test@gmail.ru";
  private static final String JONNY_FIRSTNAME = "John";
  private static final String JONNY_LASTNAME = "Test";

  public static User.UserBuilder userEntityBuilder() {
    return User.builder()
        .email(JONNY_EMAIL)
        .firstname(JONNY_FIRSTNAME)
        .lastname(JONNY_LASTNAME)
        .password(ENCODE_PASSWORD)
        .roles(Set.of(defaultRole()));
  }

  public static RegistrationRequest createUserRequest() {
    return RegistrationRequest.builder()
        .email(JONNY_EMAIL)
        .firstname(JONNY_FIRSTNAME)
        .lastname(JONNY_LASTNAME)
        .password(DEFAULT_PASSWORD)
        .build();
  }

  public static UserInfoResponse userInfoResponse() {
    return UserInfoResponse.builder()
        .email(JONNY_EMAIL)
        .firstname(JONNY_FIRSTNAME)
        .lastname(JONNY_LASTNAME)
        .roles(Set.of(defaultRole()))
        .build();
  }

  public static LoginRequest loginRequest() {
    return LoginRequest.builder().email(JONNY_EMAIL).password(DEFAULT_PASSWORD).build();
  }

  public static Roles defaultRole() {
    return Roles.builder().id(2L).name(ROLE_USER).build();
  }
}
