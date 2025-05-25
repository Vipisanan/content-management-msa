package com.vp.content_service.mapper;


import com.vp.content_service.dto.ContentEvent;
import com.vp.content_service.dto.enums.NotificationType;
import com.vp.content_service.model.Category;
import com.vp.content_service.model.Content;

import java.util.stream.Collectors;

public class ContentEventMapper {
    public static ContentEvent toEvent(Content content, NotificationType type) {
        return ContentEvent.builder()
                .contentId(content.getId())
                .writerId(content.getWriterId())
                .type(type)
                .categoryIds(
                        content.getCategories()
                                .stream()
                                .map(Category::getId)
                                .collect(Collectors.toList()))
                .title(content.getTitle())
                .summary(content.getDetails())
                .build();
    }
}
