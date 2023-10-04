package com.platform.momentum.service;

import com.platform.momentum.entity.UserEntity;
import com.platform.momentum.model.LoginResponse;
import com.platform.momentum.repository.UserRepository;
import com.platform.momentum.security.JwtIssuer;
import com.platform.momentum.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public LoginResponse attemptLogin(String email, String password) {
        //if user has entered invalid credentials, the process will fail here
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
        return LoginResponse.builder()
                .token(token)
                .build();
    }

    public void register(UserEntity request) {
        userRepository.save(request);
    }
}
