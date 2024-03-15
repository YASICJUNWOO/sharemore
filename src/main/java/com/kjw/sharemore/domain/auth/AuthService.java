package com.kjw.sharemore.domain.auth;

import com.kjw.sharemore.global.security.CustomUserService;
import com.kjw.sharemore.domain.users.entity.Users;
import com.kjw.sharemore.domain.users.repository.UserRepository;
import com.kjw.sharemore.global.security.jwt.dto.TokenInfo;
import com.kjw.sharemore.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CustomUserService customUserService;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    public TokenInfo login(LoginDto dto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(token);

        TokenInfo tokenInfo = tokenProvider.generateToken(authenticate);
        String userName = getUserName(authenticate);

        return addUserInfo(tokenInfo.getUserEmail(), tokenInfo);
    }

    public TokenInfo addUserInfo(String email, TokenInfo tokenInfo) {
        Users user = userRepository.findByEmail(email).orElseThrow();
        return tokenInfo.addUserInfo(user);
    }

    private String getUserName(Authentication authenticate){
        return customUserService.loadUserByUsername(authenticate.getName()).getName();
    }
}
