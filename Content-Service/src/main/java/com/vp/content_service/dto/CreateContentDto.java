package com.vp.content_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class CreateContentDto {

    private Long writerId;
    private String title;
    private String details;
    private Set<String> categoryNames;
}
