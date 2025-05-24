package com.vp.user_service.controller;


import com.vp.user_service.dto.ProfileDto;
import com.vp.user_service.dto.ProfileUpdateRequest;
import com.vp.user_service.dto.UserCreateRequest;
import com.vp.user_service.dto.UserDto;
import com.vp.user_service.mapper.UserMapper;
import com.vp.user_service.model.User;
import com.vp.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> dtos = UserMapper.toDtoList(users);
        return ResponseEntity.ok(dtos);
    }

    // Get a user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(u -> ResponseEntity.ok(UserMapper.toDto(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get a user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(u -> ResponseEntity.ok(UserMapper.toDto(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Update profile for a user (since email/password cannot be updated)
    @PutMapping("/{id}/profile")
    public ResponseEntity<ProfileDto> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileUpdateRequest request) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        if (user.getProfile() == null) {
            return ResponseEntity.badRequest().build();
        }
        UserMapper.updateProfileFromDto(request, user.getProfile());
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(UserMapper.toDto(updatedUser.getProfile()));
    }
}