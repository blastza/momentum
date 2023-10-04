package com.platform.momentum.service;

import com.platform.momentum.entity.UserEntity;
import com.platform.momentum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserEntity> findUserByEmail(String email){
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        if(byEmail.get().getEmail().equalsIgnoreCase(email)){

        }

        if(byEmail.isEmpty())
            throw new UsernameNotFoundException("User does not exist");
        return byEmail;

    }
}
