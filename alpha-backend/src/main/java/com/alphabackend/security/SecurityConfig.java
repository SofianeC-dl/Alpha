package com.alphabackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    List<String> authzRootPattern = List.of(
            "/project/**",
            "/projects/**",

            "/tag/**",
            "/tags/**",

            "/imageProject/**",
            "/imageProjects/**",

            "/imageIllustration/**",
            "/imageIllustrations/**",

            "/imageIllustration/**",
            "/imageIllustrations/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Dans Spring Security 6, on utilise "requestMatchers(...)"
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(authzRootPattern.toArray(new String[0])).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
