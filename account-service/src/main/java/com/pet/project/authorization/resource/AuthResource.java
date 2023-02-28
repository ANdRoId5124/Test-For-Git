package com.pet.project.authorization.resource;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pet.project.authorization.domain.request.LoginRequest;
import com.pet.project.authorization.domain.request.RegistrationRequest;
import com.pet.project.authorization.domain.response.ErrorResponse;
import com.pet.project.authorization.service.TokenService;
import com.pet.project.authorization.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/v1/auth")
public class AuthResource {

    private static final String LOGOUT_MESSAGE = "You've been sighed out!";
    private static final String REFRESH_TOKEN_MESSAGE = "Token is refreshed successfully!";
    private final TokenService tokenService;
    private final UserService userService;

    public AuthResource(final TokenService tokenService, final UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> auth(@Valid @RequestBody final LoginRequest request) {
        return ok().header(SET_COOKIE, tokenService.generateAccessToken(request))
                .header(SET_COOKIE, tokenService.generateRefreshToken(request.getEmail())).build();
    }

    @Operation(summary = "Add new user", description = "Endpoint for added user", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
    @PostMapping("/registration")
    public ResponseEntity<Long> registration(@Valid @RequestBody final RegistrationRequest request) {
        return new ResponseEntity<>(userService.createUser(request), CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        return ok().header(SET_COOKIE, tokenService.cleanAccessToken())
                .header(SET_COOKIE, tokenService.cleanRefreshToken()).body(LOGOUT_MESSAGE);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<String> refreshToken(final HttpServletRequest request) {
        return ResponseEntity.ok().header(SET_COOKIE, tokenService.findByToken(request)).body(REFRESH_TOKEN_MESSAGE);
    }
}
