package com.vp.content_service.service.impl;

import com.vp.content_service.constant.AppConstant;
import com.vp.content_service.dto.CategoryCreateRequest;
import com.vp.content_service.dto.ContentDto;
import com.vp.content_service.dto.ContentEvent;
import com.vp.content_service.dto.CreateContentDto;
import com.vp.content_service.dto.enums.NotificationType;
import com.vp.content_service.mapper.ContentEventMapper;
import com.vp.content_service.mapper.ContentMapper;
import com.vp.content_service.model.Category;
import com.vp.content_service.model.Content;
import com.vp.content_service.repository.CategoryRepository;
import com.vp.content_service.repository.ContentRepository;
import com.vp.content_service.service.CategoryService;
import com.vp.content_service.service.ContentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final ContentMapper contentMapper;
    private final CategoryService categoryService;
    private final KafkaTemplate<String, ContentEvent> kafkaTemplate;


    @Override
    public ContentDto createContent(CreateContentDto createContentDto) {

        // Fetch categories by name if not exist crete a category
        Set<Category> categories = Collections.emptySet();
        if (createContentDto.getCategoryNames() != null && !createContentDto.getCategoryNames().isEmpty()) {

//            TODO: when crete a category here, Notification service doesn't read it
            createContentDto.getCategoryNames().forEach(category -> {
                Optional<Category> existedCategory = categoryRepository.findByNameIgnoreCase(category);
                if (!existedCategory.isPresent()) {
                    CategoryCreateRequest categoryCreateRequest = new CategoryCreateRequest(category);
                    categoryService.createCategory(categoryCreateRequest);
                }
            });
            categories = new HashSet<>(categoryRepository.findByNameIn(createContentDto.getCategoryNames()));
        }

        // Map to entity
        Content content = contentMapper.toEntity(createContentDto, categories);
        LocalDateTime now = LocalDateTime.now();
        content.setDatePublished(now);
        content.setUpdatedAt(now);
        // Save and map back to DTO
        Content saved = contentRepository.save(content);
        ContentEvent contentEvent = ContentEventMapper.toEvent(saved, NotificationType.PUBLISHED);
        kafkaTemplate.send(AppConstant.CONTENT_EVENT_KTP, contentEvent);
        return contentMapper.toDto(saved);
    }

    @Override
    public Optional<ContentDto> getContentById(Long id) {
        return contentRepository.findById(id).map(contentMapper::toDto);
    }

    @Override
    public Page<ContentDto> getAllContents(Pageable pageable) {
        Page<Content> page = contentRepository.findAll(pageable);
        return new PageImpl<>(page.getContent().stream().map(contentMapper::toDto).collect(Collectors.toList()), pageable, page.getTotalElements());
    }

    @Override
    public ContentDto updateContent(Long id, ContentDto contentDto) {
        Optional<Content> contentOpt = contentRepository.findById(id);
        if (contentOpt.isEmpty()) throw new RuntimeException("Content not found");
        Content content = contentOpt.get();

        content.setTitle(contentDto.getTitle());
        content.setDetails(contentDto.getDetails());
        content.setDatePublished(contentDto.getDatePublished());
        content.setUpdatedAt(contentDto.getUpdatedAt());

        if (contentDto.getCategoryNames() != null) {
            Set<Category> categories = contentDto.getCategoryNames().stream().map(categoryRepository::findByName).collect(Collectors.toSet());
            content.setCategories(categories);
        }

        Content updated = contentRepository.save(content);
        ContentEvent contentEvent = ContentEventMapper.toEvent(updated, NotificationType.UPDATED);
        kafkaTemplate.send(AppConstant.CONTENT_EVENT_KTP, contentEvent);
        return contentMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteContent(Long id) {
        // Fetch content with categories eagerly
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with id: " + id));
        // Map your event BEFORE deleting
        ContentEvent contentEvent = ContentEventMapper.toEvent(content, NotificationType.DELETED);
        // Now it's safe to delete the entity
        contentRepository.delete(content);
        // Send event
        kafkaTemplate.send(AppConstant.CONTENT_EVENT_KTP, contentEvent);

    }
}