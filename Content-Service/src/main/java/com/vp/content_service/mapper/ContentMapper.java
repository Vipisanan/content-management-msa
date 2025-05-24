package com.vp.content_service.mapper;

import com.vp.content_service.dto.ContentDto;
import com.vp.content_service.dto.CreateContentDto;
import com.vp.content_service.model.Category;
import com.vp.content_service.model.Content;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ContentMapper {

    // Entity → DTO
    public ContentDto toDto(Content content) {
        if (content == null) return null;
        ContentDto dto = new ContentDto();
        dto.setId(content.getId());
        dto.setWriterId(content.getWriterId());
        dto.setTitle(content.getTitle());
        dto.setDetails(content.getDetails());
        dto.setDatePublished(content.getDatePublished());
        dto.setUpdatedAt(content.getUpdatedAt());
        dto.setCategoryNames(content.getCategories() != null ? content.getCategories().stream().map(Category::getName).collect(Collectors.toSet()) : Collections.emptySet());
        return dto;
    }

    // ContentDto → Entity
    public Content toEntity(ContentDto dto, Set<Category> categories) {
        if (dto == null) return null;
        Content content = new Content();
        content.setId(dto.getId());
        content.setTitle(dto.getTitle());
        content.setDetails(dto.getDetails());
        content.setDatePublished(dto.getDatePublished());
        content.setUpdatedAt(dto.getUpdatedAt());
        content.setWriterId(dto.getWriterId());
        content.setCategories(categories);
        return content;
    }

    // DTO → Entity (expects User and Set<Category> to be provided)
    public Content toEntity(CreateContentDto dto, Set<Category> categories) {
        if (dto == null) return null;
        Content content = new Content();
        content.setTitle(dto.getTitle());
        content.setDetails(dto.getDetails());
        content.setWriterId(dto.getWriterId());
        content.setCategories(categories);
        return content;
    }

    // List<Entity> → List<DTO>
    public List<ContentDto> toDtoList(List<Content> contents) {
        if (contents == null) return Collections.emptyList();
        return contents.stream().map(this::toDto).collect(Collectors.toList());
    }


}