package com.vp.user_service.controller;

import com.vp.user_service.dto.LoginRequestDto;
import com.vp.user_service.dto.LoginResponseDto;
import com.vp.user_service.dto.UserCreateRequest;
import com.vp.user_service.dto.UserDto;
import com.vp.user_service.mapper.UserMapper;
import com.vp.user_service.model.User;
import com.vp.user_service.service.AuthService;
import com.vp.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto loginResponseDto = authService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(loginResponseDto);
    }
    // Register a new user
    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserCreateRequest request) {
        User created = authService.registerUser(request);
        return ResponseEntity.ok(UserMapper.toDto(created));
    }
}
