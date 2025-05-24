package com.vp.user_service.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String accessToken;

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
