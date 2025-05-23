package com.vp.user_service.dto;

import lombok.Data;

@Data
public class ProfileDto {
    private Long id;
    private String displayName;
    private String bio;
    private String country;
}
