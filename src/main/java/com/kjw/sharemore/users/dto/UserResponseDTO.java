package com.kjw.sharemore.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String name;
    private String email;
    private String password;
    private String address;

}
