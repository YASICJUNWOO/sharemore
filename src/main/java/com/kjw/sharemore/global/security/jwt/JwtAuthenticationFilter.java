package com.kjw.sharemore.global.security.jwt;

import com.kjw.sharemore.apiPayLoad.code.status.ErrorStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //헤더 검증
        String bearerToken  = request.getHeader(TokenProvider.AUTHORIZATION_HEADER);

        //토큰 검증
        String accessToken = getAccessToken(bearerToken);
        if(StringUtils.hasText(accessToken) && validateToken(accessToken,request)){
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            log.info(authentication.getName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("# Token verification success!");
        }

        filterChain.doFilter(request,response);
    }

    //bearerToken 에서 token 가져오기
    private String getAccessToken(String bearerToken){
        if(StringUtils.hasText(bearerToken) &&
                bearerToken.startsWith(TokenProvider.PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateToken(String accessToken,HttpServletRequest req) throws IOException {
        try{
            Claims parse = tokenProvider.parse(accessToken);
        }
        catch (ExpiredJwtException e){
            req.setAttribute("exception","1001");
        }
        catch (RuntimeException e) {
            log.info(e.getMessage());
        }
        return true;
    }

}