package com.kjw.sharemore.global.security.jwt;

import com.kjw.sharemore.domain.users.entity.Users;
import com.kjw.sharemore.domain.users.service.UserQueryService;
import com.kjw.sharemore.domain.users.service.UserService;
import com.kjw.sharemore.global.security.jwt.dto.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final int EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;

    private final UserService userService;
    private final UserQueryService userQueryService;
    public static final String BEARER_TYPE = "Bearer";
    public static final String PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Value("${jwt.secret}")
    private String secretKey;

    private Key key;

    @PostConstruct
    public void init(){
        //인코딩 키
        String encode = Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
        this.key = getKey(encode);
    }

    private Key getKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //User 정보로 토큰을 만든다
    public TokenInfo generateToken(Authentication authentication){

        Date now = new Date();
        Date accessTokenExpire = new Date(now.getTime() + EXPIRE_TIME);
        //Date refreshTokenExpire = new Date(now)

        //claim 설정
        Map<String,Object> claims = new HashMap<>();
        claims.put("rols",authentication.getAuthorities());

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(authentication.getName())
                .setExpiration(accessTokenExpire)
                .setIssuedAt(Calendar.getInstance().getTime())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        //todo : 권한 정보 추가
        return TokenInfo.builder()
                .accessToken(accessToken)
                .userEmail(authentication.getName())
                .build();

    }

    //토큰 파싱
    public Claims parse(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String accessToken){
        Claims claims = parse(accessToken);
        Users users = userQueryService.getUserByEmail(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(users,null,users.getAuthorities());
    }


}
