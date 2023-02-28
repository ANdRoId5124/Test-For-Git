package com.pet.project.authorization.serialisation;

import org.junit.jupiter.api.BeforeEach;
import com.pet.project.authorization.domain.request.RegistrationRequest;
import com.pet.project.authorization.helper.UserHelper;

public class RegistrationRequestSerialisationTest extends JsonTestBase<RegistrationRequest> {

  @BeforeEach
  void beforeEach() {
    expected = UserHelper::createUserRequest;
    fileName = "expected_registration_request.json";
    expectedType = RegistrationRequest.class;
  }
}
