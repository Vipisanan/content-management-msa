package com.vp.user_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonBackReference
    private User user;

    private String displayName;
    private String bio;
    private String country;

    public boolean isCompleted() {
        return displayName != null && !displayName.isBlank()
                && bio != null && !bio.isBlank()
                && country != null && !country.isBlank();
    }
}