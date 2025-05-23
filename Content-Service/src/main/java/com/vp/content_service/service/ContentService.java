package com.vp.content_service.service;

import com.vp.content_service.dto.ContentDto;
import com.vp.content_service.dto.CreateContentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContentService {
    ContentDto createContent(CreateContentDto contentDto);

    Optional<ContentDto> getContentById(Long id);

    Page<ContentDto> getAllContents(Pageable pageable);

    ContentDto updateContent(Long id, ContentDto contentDto);

    void deleteContent(Long id);
}