package com.vp.user_service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSigninResponseDto {
    private String token;
    private Long userId;
}
