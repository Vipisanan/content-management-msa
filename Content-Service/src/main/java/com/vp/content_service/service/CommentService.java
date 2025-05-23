package com.vp.content_service.service;

import com.vp.content_service.dto.CommentCreateRequest;
import com.vp.content_service.dto.CommentDto;
import com.vp.content_service.dto.CommentUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    CommentDto createComment(CommentCreateRequest request);

    Optional<CommentDto> getCommentById(Long id);

    List<CommentDto> getAllComments();

    CommentDto updateComment(Long id, CommentUpdateRequest request);

    void deleteComment(Long id);
}
