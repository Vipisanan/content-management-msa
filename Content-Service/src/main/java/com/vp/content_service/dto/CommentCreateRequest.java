package com.vp.content_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentCreateRequest {
    @NotNull(message = "Content ID is required")
    private Long contentId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Text is required")
    private String text;
}
