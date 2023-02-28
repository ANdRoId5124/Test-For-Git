package com.pet.project.authorization.serialisation;

import org.junit.jupiter.api.BeforeEach;
import com.pet.project.authorization.domain.response.ErrorResponse;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

public class ErrorResponseSerialisationTest extends JsonTestBase<ErrorResponse> {

  @BeforeEach
  void beforeEach() {
    expected =
        () ->
            ErrorResponse.builder()
                .statusCode(SC_FORBIDDEN)
                .message("error message")
                .description("error description")
                .build();
    fileName = "expected_error_response.json";
    expectedType = ErrorResponse.class;
  }
}
