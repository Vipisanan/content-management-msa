package com.vp.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileUpdateRequest {
    @NotBlank(message = "Display name is required")
    @Size(min = 2, max = 50, message = "Display name should be between 2 and 50 characters")
    private String displayName;

    @NotBlank(message = "Bio is required")
    @Size(min = 5, max = 255, message = "Bio should be between 5 and 255 characters")
    private String bio;

    @NotBlank(message = "Country is required")
    private String country;
}
