package com.bloggingapp.bloggingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;
    AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        authenticationManager = authenticationManagerBuilder.build();
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/**").permitAll();              
            auth.anyRequest().authenticated();
            })
            .authenticationManager(authenticationManager);
        return http.build();
    }

    // @Bean
    // public AuthenticationManager authenticationManager(HttpSecurity http) 
    // throws Exception {
    //     return http.getSharedObject(AuthenticationManagerBuilder.class)
    //     .userDetailsService(userDetailService)
    //     .passwordEncoder(passwordEncoder())
    //     .and()
    //     .build();
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
