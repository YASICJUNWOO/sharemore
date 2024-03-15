package com.kjw.sharemore.global.config.security;

import com.kjw.sharemore.global.security.CustomPasswordEncoder;
import com.kjw.sharemore.global.security.jwt.JwtAuthenticationEntryPoint;
import com.kjw.sharemore.global.security.jwt.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.*;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .headers(headers->
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/status/**","/auth","/api/item/**","/**").permitAll()
                        .requestMatchers(GET,"/api/item","/api/reservation/**").permitAll()
                        .anyRequest().authenticated()
                )
                /*.exceptionHandling(handler->
                        handler.authenticationEntryPoint(jwtAuthenticationEntryPoint))*/
                .sessionManagement(session->
                        session.sessionCreationPolicy(STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                /*.httpBasic(withDefaults())
                .formLogin(login->login
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/status/ok", true)
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll()
                )*/


        return http.build();
    }



}
