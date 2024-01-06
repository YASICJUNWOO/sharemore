package com.kjw.sharemore.auth;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.auth.dto.LoginDto;
import com.kjw.sharemore.auth.dto.TokenDto;
import com.kjw.sharemore.global.security.filter.JwtAuthorizationFilter;
import com.kjw.sharemore.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    //토큰 생성 및 검증을 수행할 클래스
    private final TokenProvider tokenProvider;
    //로그인 처리를 수행할 클래스
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {

        //로그인 정보 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        //로그인 처리
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //로그인 정보를 SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //토큰 생성
        String token = tokenProvider.createToken(authentication);

        //토큰을 Response Header에도 넣어줌
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtAuthorizationFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        return ResponseEntity.ok().headers(httpHeaders).body(new TokenDto(token));
    }
}
