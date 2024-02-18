package com.kjw.sharemore.global.security;

import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final UserService userService;

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username: {}", username);

        return userService.getUserByEmail(username);
    }


}
