package com.vp.user_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String email;
    private LocalDateTime createdAt;
    private ProfileDto profile;
    private boolean canPublish;
}
