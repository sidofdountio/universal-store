package com.meche.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS;

@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:80","http://localhost:4200","http://localhost:8080"));
        configuration.setAllowedHeaders(Arrays.asList(
                ACCEPT,
                AUTHORIZATION,
                CONTENT_TYPE,
                ORIGIN,"Jwt-Token",
                ACCESS_CONTROL_ALLOW_ORIGIN,
                ACCESS_CONTROL_REQUEST_METHOD,
                ACCESS_CONTROL_ALLOW_HEADERS,
                ACCESS_CONTROL_REQUEST_HEADERS,"X-Requested-With","Origin, Accept"
        ));
        configuration.setExposedHeaders(Arrays.asList(ACCEPT,
                AUTHORIZATION,
                CONTENT_TYPE,
                ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN,
                ACCESS_CONTROL_ALLOW_CREDENTIALS,
                "Jwt-Token","Filename"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}