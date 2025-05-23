package com.vp.content_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private Long contentId;
    private Long userId;
    private String text;
    private LocalDateTime createdAt;
}