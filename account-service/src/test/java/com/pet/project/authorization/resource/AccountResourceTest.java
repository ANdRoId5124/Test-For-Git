package com.pet.project.authorization.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.only;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.ok;
import static com.pet.project.authorization.helper.UserHelper.userInfoResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import com.pet.project.authorization.service.UserService;

class AccountResourceTest {

    private static final Long ID = 3L;

    @Mock
    private UserService userService;
    @InjectMocks
    private AccountResource accountResource;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    void shouldReturnUserInfo() {
        given(userService.getUserInfo(ID)).willReturn(userInfoResponse());

        assertThat(accountResource.getUserInfo(ID), is(ok(userInfoResponse())));

        then(userService).should(only()).getUserInfo(ID);
    }

    @Test
    void shouldDeleteUserById() {
        willDoNothing().given(userService).delete(ID);

        assertThat(accountResource.delete(ID), is(new ResponseEntity<>(NO_CONTENT)));

        then(userService).should(only()).delete(ID);
    }

}
