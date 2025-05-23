package com.vp.content_service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentDto {
    private Long id;
    private Long writerId; // email or username, as you prefer
    private String title;
    private String details;
    private LocalDateTime datePublished;
    private LocalDateTime updatedAt;
    private Set<String> categoryNames;
}
