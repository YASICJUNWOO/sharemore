package com.kjw.sharemore.global.security.jwt.dto;

import com.kjw.sharemore.domain.users.dto.UserResponseDTO;
import com.kjw.sharemore.domain.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenInfo {

    private String grandType;

    private String accessToken;

    private String userEmail;

    private UserResponseDTO.Simple userInfo;

    public TokenInfo addUserInfo(Users user) {
        this.userInfo = UserResponseDTO.Simple.of(user);
        return this;
    }

}
