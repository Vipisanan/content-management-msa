package com.vp.content_service.repository;

import com.vp.content_service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByContentId(Long contentId);

}