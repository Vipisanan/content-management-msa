package com.vp.content_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "writer_id", nullable = false)
    private Long writerId;

    private String title;

    @Lob
    @Column
    private String details;

    private LocalDateTime datePublished;
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "content_category",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;
}