package com.pet.project.authorization.serialisation;

import org.junit.jupiter.api.BeforeEach;
import com.pet.project.authorization.domain.response.UserInfoResponse;
import com.pet.project.authorization.helper.UserHelper;

public class UserInfoResponseSerialisationTest extends JsonTestBase<UserInfoResponse> {

    @BeforeEach
    void beforeEach() {
        expected = UserHelper::userInfoResponse;
        fileName = "expected_user_info_response.json";
        expectedType = UserInfoResponse.class;
    }
}
