package com.vp.content_service.controller;

import com.vp.content_service.dto.ContentDto;
import com.vp.content_service.dto.CreateContentDto;
import com.vp.content_service.dto.PagedResponse;
import com.vp.content_service.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<ContentDto> createContent(@RequestBody CreateContentDto contentDto) {
        ContentDto created = contentService.createContent(contentDto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentDto> getContent(@PathVariable Long id) {
        return contentService.getContentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<PagedResponse<ContentDto>> getAllContents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ContentDto> contentPage = contentService.getAllContents(pageable);

        PagedResponse<ContentDto> response = PagedResponse.<ContentDto>builder()
                .data(contentPage.getContent())
                .pageNumber(contentPage.getNumber())
                .pageSize(contentPage.getSize())
                .totalElements(contentPage.getTotalElements())
                .totalPages(contentPage.getTotalPages())
                .last(contentPage.isLast())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentDto> updateContent(@PathVariable Long id, @RequestBody ContentDto contentDto) {
        ContentDto updated = contentService.updateContent(id, contentDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}