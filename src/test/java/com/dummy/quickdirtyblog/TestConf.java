package com.dummy.quickdirtyblog;

import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
@Order(1)
public class TestConf {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return // Disable CSRF
    http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        .csrf(CsrfConfigurer::disable)
        .build();
  }
}
