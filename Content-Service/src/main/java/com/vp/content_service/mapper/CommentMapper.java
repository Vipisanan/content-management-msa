package com.vp.content_service.mapper;

import com.vp.content_service.dto.CommentCreateRequest;
import com.vp.content_service.dto.CommentDto;
import com.vp.content_service.dto.CommentUpdateRequest;
import com.vp.content_service.model.Comment;
import com.vp.content_service.model.Content;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {

    public Comment toEntity(CommentCreateRequest dto, Content content) {
        return Comment.builder()
                .content(content)
                .userId(dto.getUserId())
                .text(dto.getText())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public CommentDto toDto(Comment comment) {
        if (comment == null) return null;
        return CommentDto.builder()
                .id(comment.getId())
                .contentId(comment.getContent() != null ? comment.getContent().getId() : null)
                .userId(comment.getUserId())
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public void updateEntity(CommentUpdateRequest dto, Comment comment) {
        if (dto.getText() != null) {
            comment.setText(dto.getText());
        }
    }
}
