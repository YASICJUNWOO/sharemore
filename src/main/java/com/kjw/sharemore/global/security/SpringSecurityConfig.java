package com.kjw.sharemore.global.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filerChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(request-> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                       //.requestMatchers("/api/users/**","/status/**","/h2-console/**")
                        .requestMatchers("**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable) //h2-console관련
                )
                .formLogin(login -> login
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .failureForwardUrl("/status/fail")
                        .defaultSuccessUrl("/status/ok",true)
                        .permitAll()
                )
                .logout(Customizer.withDefaults());

        return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new SimplePaaswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
