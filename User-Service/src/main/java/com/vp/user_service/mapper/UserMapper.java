package com.vp.user_service.mapper;


import com.vp.user_service.dto.*;
import com.vp.user_service.model.Profile;
import com.vp.user_service.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    // Convert UserCreateRequest DTO to User entity
    public static User toUser(UserCreateRequest dto) {
        if (dto == null) return null;
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPasswordHash(dto.getPassword()); // Password encoding should be handled in service layer!
        if (dto.getProfile() != null) {
            user.setProfile(toProfile(dto.getProfile(), user));
        }
        return user;
    }

    // Convert ProfileCreateRequest DTO to Profile entity
    public static Profile toProfile(ProfileCreateRequest dto, User user) {
        if (dto == null) return null;
        Profile profile = new Profile();
        profile.setDisplayName(dto.getDisplayName());
        profile.setBio(dto.getBio());
        profile.setCountry(dto.getCountry());
        profile.setUser(user);
        return profile;
    }

    // Convert User entity to UserDto
    public static UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setProfile(toDto(user.getProfile()));
        dto.setCanPublish(user.canPublish());
        return dto;
    }

    // Convert Profile entity to ProfileDto
    public static ProfileDto toDto(Profile profile) {
        if (profile == null) return null;
        ProfileDto dto = new ProfileDto();
        dto.setId(profile.getId());
        dto.setDisplayName(profile.getDisplayName());
        dto.setBio(profile.getBio());
        dto.setCountry(profile.getCountry());
        return dto;
    }

    // Convert ProfileUpdateRequest DTO to existing Profile entity (update in-place)
    public static void updateProfileFromDto(ProfileUpdateRequest dto, Profile profile) {
        if (dto == null || profile == null) return;
        profile.setDisplayName(dto.getDisplayName());
        profile.setBio(dto.getBio());
        profile.setCountry(dto.getCountry());
    }

    // Convert List<User> -> List<UserDto>
    public static List<UserDto> toDtoList(List<User> users) {
        if (users == null) return null;
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}