package com.alobcan.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConf {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/holidays/**").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/savemsg").permitAll()
                .requestMatchers("/courses").authenticated()
                .requestMatchers("/about").authenticated()
                .requestMatchers("/assets/**").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }
}
