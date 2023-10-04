package com.platform.momentum.controller;

import com.platform.momentum.entity.UserEntity;
import com.platform.momentum.model.LoginRequest;
import com.platform.momentum.model.LoginResponse;
import com.platform.momentum.security.JwtIssuer;
import com.platform.momentum.security.UserPrincipal;
import com.platform.momentum.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
   private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/register")
    public void register(
            @RequestBody UserEntity request, @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        authService.register(request);
    }

}
