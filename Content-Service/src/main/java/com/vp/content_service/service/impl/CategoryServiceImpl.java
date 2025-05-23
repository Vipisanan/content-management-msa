package com.vp.content_service.service.impl;


import com.vp.content_service.dto.CategoryCreateRequest;
import com.vp.content_service.dto.CategoryDto;
import com.vp.content_service.dto.CategoryUpdateRequest;
import com.vp.content_service.model.Category;
import com.vp.content_service.repository.CategoryRepository;
import com.vp.content_service.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(@Valid CategoryCreateRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new DataIntegrityViolationException("Category name already exists");
        }
        Category category = Category.builder()
                .name(request.getName())
                .build();
        return toDto(categoryRepository.save(category));
    }

    @Override
    public Optional<CategoryDto> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, @Valid CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        if (!category.getName().equals(request.getName()) && categoryRepository.existsByName(request.getName())) {
            throw new DataIntegrityViolationException("Category name already exists");
        }

        category.setName(request.getName());
        return toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    private CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
