package com.dummy.quickdirtyblog.config;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@Profile("!test")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  private final JwtAuthConverter jwtAuthConverter;
  private final KeycloakLogoutHandler keycloakLogoutHandler;

  public SecurityConfig(
      JwtAuthConverter jwtAuthConverter, KeycloakLogoutHandler keycloakLogoutHandler) {
    this.jwtAuthConverter = jwtAuthConverter;
    this.keycloakLogoutHandler = keycloakLogoutHandler;
  }

  @Bean
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(CsrfConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(HttpMethod.GET, "/api/v1/blogs/{id}")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .headers(
            httpSecurityHeadersConfigurer ->
                httpSecurityHeadersConfigurer.frameOptions(
                    HeadersConfigurer.FrameOptionsConfig::disable))
        .oauth2Login(Customizer.withDefaults())
        .logout(
            logout ->
                logout.addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl("/api/v1/blogs"))
        .oauth2ResourceServer(
            authServer -> authServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
        .build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:8080/"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "UPDATE", "DELETE"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public AuthenticationSuccessHandler successHandler() {
    SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
    handler.setUseReferer(true);
    return handler;
  }
}
