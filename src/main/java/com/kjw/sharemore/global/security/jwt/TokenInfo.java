package com.kjw.sharemore.global.security.jwt;

import com.kjw.sharemore.users.dto.UserResponseDTO;
import com.kjw.sharemore.users.entity.Users;
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

    private UserResponseDTO userInfo;

    public TokenInfo addUserInfo(Users user) {
        this.userInfo = UserResponseDTO.of(user);
        return this;
    }

}
