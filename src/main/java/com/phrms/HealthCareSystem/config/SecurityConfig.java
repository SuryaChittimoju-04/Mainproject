package com.phrms.HealthCareSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry.requestMatchers("/HCS/**").permitAll()
                        .anyRequest().authenticated()).exceptionHandling(Customizer.withDefaults()).sessionManagement(sessionManagement->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        httpSecurity.addFilterBefore()
        return httpSecurity.build();
    }
}
