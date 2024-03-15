package com.kjw.sharemore.domain.users.dto;

import com.kjw.sharemore.domain.users.entity.Users;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String address;

    public static Users toEntity(UserRequestDTO userRequestDTO) {
        return Users.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .address(userRequestDTO.getAddress())
                .build();
    }

}
