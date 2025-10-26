package com.hacktheheights.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityDisableConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // optional, allows POST requests from JS
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // allow all pages
        return http.build();
    }
}
