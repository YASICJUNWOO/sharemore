package com.kjw.sharemore.global.security.jwt;

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

    private String userName;

}
