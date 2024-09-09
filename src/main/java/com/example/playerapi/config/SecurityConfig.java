package com.example.playerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Classe de configuration pour Spring Security.
 * Définit les règles de sécurité et les détails des utilisateurs en mémoire pour l'authentification.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/players/**").authenticated()
                                .anyRequest().permitAll()
                )
                .httpBasic(withDefaults())  // Permet l'authentification de base HTTP
                .formLogin(withDefaults())  // Permet le formulaire de connexion
                .csrf(AbstractHttpConfigurer::disable);  // Désactive CSRF pour les tests

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        return new InMemoryUserDetailsManager(
                users.username("maliimaloo").password("password").roles("USER").build()
        );
    }
}
