package com.kjw.sharemore.global.security;

import com.kjw.sharemore.global.security.filter.JwtAuthorizationFilter;
import com.kjw.sharemore.global.security.jwt.TokenProvider;
import com.kjw.sharemore.global.security.jwt.except.JwtAccessDeniedHandler;
import com.kjw.sharemore.global.security.jwt.except.JwtAuthenticationEntryPoint;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    //권한이 없는 사용자가 접근했을 때 처리하는 클래스
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    //토큰 생성 및 검증을 수행할 클래스
    private final TokenProvider jwtProvider;

    @Bean
    public SecurityFilterChain filerChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                //예외처리
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                //세션 사용하지 않음
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                //h2-console관련
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )

                //권한 설정
                .authorizeHttpRequests(request-> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/api/users/**","/status/**","/h2-console/**","/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                .addFilterBefore(new JwtAuthorizationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

                /*.formLogin(login -> login
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .failureForwardUrl("/status/fail")
                        .defaultSuccessUrl("/status/ok",true)
                        .permitAll()
                )
                .logout(Customizer.withDefaults());*/

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new SimplePaaswordEncoder();
    }

}
