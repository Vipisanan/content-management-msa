package com.vp.content_service.service;

import com.vp.content_service.dto.CategoryCreateRequest;
import com.vp.content_service.dto.CategoryDto;
import com.vp.content_service.dto.CategoryUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDto createCategory(CategoryCreateRequest request);

    Optional<CategoryDto> getCategoryById(Long id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(Long id, CategoryUpdateRequest request);

    void deleteCategory(Long id);
}
