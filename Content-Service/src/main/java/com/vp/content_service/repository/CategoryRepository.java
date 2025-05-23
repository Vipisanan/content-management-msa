package com.vp.content_service.repository;

import com.vp.content_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);

    Category findByName(String name);

    Optional<Category> findByNameIgnoreCase(String names);

    List<Category> findByNameIn(Collection<String> names);
}