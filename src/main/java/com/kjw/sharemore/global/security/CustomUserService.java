package com.kjw.sharemore.global.security;

import com.kjw.sharemore.domain.users.entity.Users;
import com.kjw.sharemore.domain.users.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final UserQueryService userQueryService;

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username: {}", username);

        return userQueryService.getUserByEmail(username);
    }


}
