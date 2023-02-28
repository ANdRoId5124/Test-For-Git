package com.pet.project.authorization.serialisation;

import org.junit.jupiter.api.BeforeEach;
import com.pet.project.authorization.domain.request.LoginRequest;
import com.pet.project.authorization.helper.UserHelper;

public class LoginRequestSerialisationTest extends JsonTestBase<LoginRequest> {

  @BeforeEach
  void beforeEach() {
    expected = UserHelper::loginRequest;
    fileName = "expected_login_request.json";
    expectedType = LoginRequest.class;
  }
}
