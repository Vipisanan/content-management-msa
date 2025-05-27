package com.vp.user_service.service.impl;


import com.vp.user_service.dto.ProfileRequestDto;
import com.vp.user_service.dto.UserDto;
import com.vp.user_service.mapper.UserMapper;
import com.vp.user_service.model.User;
import com.vp.user_service.repository.UserRepository;
import com.vp.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).map(u -> {
            u.setEmail(updatedUser.getEmail());
            u.setPasswordHash(passwordEncoder.encode(updatedUser.getPasswordHash()));
            //TODO: Set other fields as needed
            return userRepository.save(u);
        }).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto addProfile(User user, ProfileRequestDto profileDto) {
        user = UserMapper.addProfileToUser(user, profileDto);
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDto> getUsersByIds(List<Long> userIds) {
        List<User> users = userRepository.findByIdIn(userIds);
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}