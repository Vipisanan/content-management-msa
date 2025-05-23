package com.vp.user_service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSigninRequestDto {
    private String email;
    private String password;
}
