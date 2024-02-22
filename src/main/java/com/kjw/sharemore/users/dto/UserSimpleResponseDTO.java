package com.kjw.sharemore.users.dto;

import com.kjw.sharemore.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* @Description: 기본적인 사용자 정보 응답 DTO
**/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserSimpleResponseDTO {

    private Long id;
    private String name;
    private String email;

    public static UserSimpleResponseDTO of(Users user) {
        return UserSimpleResponseDTO.builder()
                .id(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
