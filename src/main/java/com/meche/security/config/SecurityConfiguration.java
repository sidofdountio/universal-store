package com.meche.security.config;

import com.meche.security.model.Role;
import com.meche.security.service.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import static com.meche.security.model.Role.ROLE_ADMIN;
import static com.meche.security.model.Role.ROLE_USER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * @Author sidof
 * @Since 10/4/23
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutService logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        XorCsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new XorCsrfTokenRequestAttributeHandler();
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authHttpReq -> authHttpReq
                        .requestMatchers("api/v1/hair/auth/**")
                        .permitAll()
                        .requestMatchers("api/v1/hair/auth/authenticate")
                        .permitAll()
                        .requestMatchers(GET, "api/v1/hair/user/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/product/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/product-category/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/sale/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/purchase/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/invoice/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/inventory/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/employee/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/customer/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/supplier/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "api/v1/hair/charge/**").hasAnyAuthority(ROLE_ADMIN.name())

                        .requestMatchers(POST, "api/v1/hair/product/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(POST, "api/v1/hair/product-category/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(POST, "api/v1/hair/sale/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(POST, "api/v1/hair/purchase/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(POST, "api/v1/hair/invoice/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(POST, "api/v1/hair/inventory/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(POST, "api/v1/hair/employee/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(POST, "api/v1/hair/customer/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(POST, "api/v1/hair/supplier/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(POST, "api/v1/hair/charge/**").hasAnyAuthority(ROLE_ADMIN.name())

                        .requestMatchers(PUT, "api/v1/hair/product/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(PUT, "api/v1/hair/sale/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(PUT, "api/v1/hair/supplier/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(PUT, "api/v1/hair/customer/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(PUT, "api/v1/hair/employee/**").hasAnyAuthority(ROLE_ADMIN.name())

                        .requestMatchers(DELETE, "api/v1/hair/product/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(DELETE, "api/v1/hair/customer/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(DELETE, "api/v1/hair/supplier/**").hasAnyAuthority(ROLE_ADMIN.name())
                        .requestMatchers(DELETE, "api/v1/hair/employee/**").hasAnyAuthority(ROLE_ADMIN.name())

                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                        .permitAll()
                );




        return http.build();
    }
}
