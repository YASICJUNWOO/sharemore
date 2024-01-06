package com.kjw.sharemore.global.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider implements InitializingBean {

    //토큰 유효시간
    private final long tokenValidityInMilliseconds;
    private final String secret;
    private Key key;

    // 토큰에 담을 정보
    private static final String AUTHORITIES_KEY = "auth";

    public TokenProvider(
            @Value("${jwt.secret}") String secret
            , @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds){
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
    }

    //암호화 키 생성
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    //토큰 생성
    public String createToken(Authentication authentication) {

        //권한을 문자열로 바꾸기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //토큰 유효시간  성절
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName()) //토큰 제목
                .claim(AUTHORITIES_KEY, authorities) //토큰 내 정보
                .signWith(key, SignatureAlgorithm.HS512) //암호화 알고리즘
                .setExpiration(validity) //토큰 유효시간
                .compact(); //토큰 생성
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder() //토큰 파싱
                .setSigningKey(key) //암호화 키
                .build() //빌드
                .parseClaimsJws(token) //토큰 파싱
                .getBody(); //토큰 내용 가져오기

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(",")) //권한 가져오기
                        .map(SimpleGrantedAuthority::new) //권한을 문자열로 바꾸기
                        .collect(Collectors.toList()); //리스트로 변환

        //유저 객체 생성
        User principal = new User(claims.getSubject(), "", authorities);

        //유저 객체, 토큰, 권한 반환
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }


    public boolean validateToken(String token) {

        try {
            //토큰 파싱
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        }
        catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        }
        catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        }
        catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;

    }
}
