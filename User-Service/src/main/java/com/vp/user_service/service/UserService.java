package com.vp.user_service.service;

import com.vp.user_service.dto.UserResponseDto;
import com.vp.user_service.dto.UserSignupRequestDto;

import java.util.List;

public interface UserService {
    void registerUser(UserSignupRequestDto request);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();

    void deleteUser(Long id);
}