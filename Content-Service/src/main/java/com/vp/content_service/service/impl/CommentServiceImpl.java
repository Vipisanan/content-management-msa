package com.vp.content_service.service.impl;


import com.vp.content_service.dto.CommentCreateRequest;
import com.vp.content_service.dto.CommentDto;
import com.vp.content_service.dto.CommentUpdateRequest;
import com.vp.content_service.mapper.CommentMapper;
import com.vp.content_service.model.Comment;
import com.vp.content_service.model.Content;
import com.vp.content_service.repository.CommentRepository;
import com.vp.content_service.repository.ContentRepository;
import com.vp.content_service.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ContentRepository contentRepository;

    @Override
    public CommentDto createComment(CommentCreateRequest request) {

        Content content = contentRepository.findById(request.getContentId())
                .orElseThrow(() -> new RuntimeException("Content not found"));

        Comment comment = commentMapper.toEntity(request, content);

        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public Optional<CommentDto> getCommentById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::toDto);
    }

    @Override
    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(Long id, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentMapper.updateEntity(request, comment);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


    @Override
    public List<CommentDto> getCommentsByContentId(Long contentId) {
        List<Comment> comments = commentRepository.findByContentId(contentId);
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }
}
