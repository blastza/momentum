package com.platform.momentum.controller;

import com.platform.momentum.entity.UserEntity;
import com.platform.momentum.model.LoginRequest;
import com.platform.momentum.model.LoginResponse;
import com.platform.momentum.security.UserPrincipal;
import com.platform.momentum.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
   private final AuthService authService;

    @Operation(summary = "Login and get an accessToken using email & password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized - no token available"),
            @ApiResponse(responseCode = "400", description = "Bad request - something is wrong with the request")
    })
    @PostMapping("/register")
    public void register(
            @RequestBody UserEntity request, @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        authService.register(request);
    }

}
