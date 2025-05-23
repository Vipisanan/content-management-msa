package com.vp.content_service.mapper;

import com.vp.content_service.dto.CommentCreateRequest;
import com.vp.content_service.dto.CommentDto;
import com.vp.content_service.dto.CommentUpdateRequest;
import com.vp.content_service.model.Comment;
import com.vp.content_service.model.Content;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    // You will need to fetch Content and User by their IDs in your service layer.
    // Here, pass them as parameters for entity creation.
    public Comment toEntity(CommentCreateRequest dto, Content content) {
        return Comment.builder()
                .content(content)
                .userId(dto.getUserId())
                .text(dto.getText())
                .build();
    }

    // Overload for simple mapping when content and user are handled elsewhere
    public Comment toEntity(CommentCreateRequest dto) {
        Comment comment = new Comment();
        comment.setText(dto.getText());
        // content and user should be set in the service layer!
        return comment;
    }

    public CommentDto toDto(Comment comment) {
        if (comment == null) return null;
        return CommentDto.builder()
                .id(comment.getId())
                .contentId(comment.getContent() != null ? comment.getContent().getId() : null)
                .userId(comment.getUserId() != null ? comment.getId() : null)
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public void updateEntity(CommentUpdateRequest dto, Comment comment) {
        if (dto.getText() != null) {
            comment.setText(dto.getText());
        }
        // Other fields, if needed, can be updated here
    }
}
