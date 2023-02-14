package com.alobcan.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProjectSecurityConf {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers(PathRequest.toH2Console()).and()
                .authorizeHttpRequests()
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/displayMessages").hasRole("ADMIN")
                .requestMatchers("/logout").authenticated()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/holidays/**").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/courses").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/login").permitAll()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .failureForwardUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true).permitAll()
                .and().authorizeHttpRequests().requestMatchers(PathRequest.toH2Console()).permitAll()
                .and().httpBasic();

        http.headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> user = new ArrayList<>();
        user.add(User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build());
        user.add(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("USER", "ADMIN")
                .build());
        return new InMemoryUserDetailsManager(user);
    }
}
