package com.vp.user_service.service;

import com.vp.user_service.dto.ProfileRequestDto;
import com.vp.user_service.dto.UserDto;
import com.vp.user_service.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
//    User registerUser(User user);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    boolean existsByEmail(String email);

    UserDto updateUser(Long id, User updatedUser);

    void deleteUser(Long id);

    UserDto addProfile(User user, ProfileRequestDto profileDto);
}