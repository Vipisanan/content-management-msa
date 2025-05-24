package com.vp.user_service.service;

import com.vp.user_service.dto.UserCreateRequest;
import com.vp.user_service.exception.InvalidPasswordException;
import com.vp.user_service.exception.UserAlreadyExistsException;
import com.vp.user_service.exception.UserNotFoundException;
import com.vp.user_service.mapper.UserMapper;
import com.vp.user_service.model.User;
import com.vp.user_service.repository.UserRepository;
import com.vp.user_service.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new InvalidPasswordException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    public User registerUser(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new UserAlreadyExistsException("User already registered");
        }
        User user = UserMapper.toUser(userCreateRequest);
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }
}

