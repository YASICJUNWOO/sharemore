package com.kjw.sharemore.global.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SimplePaaswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
}
