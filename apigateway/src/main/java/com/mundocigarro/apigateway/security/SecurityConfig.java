package com.mundocigarro.apigateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            .sessionManagement(
                    session -> session
                            .sessionCreationPolicy(
                                    SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

                    // AUTH LIBRE
                    .requestMatchers(
                            "/api/v1/auth/**")
                    .permitAll()

                    // CLIENTES - SOLO ADMIN
                    .requestMatchers(
                            "/api/v1/clientes/**")
                    .hasRole("ADMIN")

                    // PROVEEDORES - SOLO ADMIN
                    .requestMatchers(
                            "/api/v1/proveedores/**")
                    .hasRole("ADMIN")

                    // INVENTARIO - SOLO ADMIN
                    .requestMatchers(
                            "/api/v1/inventario/**")
                    .hasRole("ADMIN")

                    // PRODUCTOS - TODOS PUEDEN VER
                    .requestMatchers(
                            HttpMethod.GET,
                            "/api/v1/productos/**")
                    .hasAnyRole(
                            "ADMIN",
                            "CLIENTE")

                    // PRODUCTOS - SOLO ADMIN MODIFICA
                    .requestMatchers(
                            HttpMethod.POST,
                            "/api/v1/productos/**")
                    .hasRole("ADMIN")

                    .requestMatchers(
                            HttpMethod.PUT,
                            "/api/v1/productos/**")
                    .hasRole("ADMIN")

                    .requestMatchers(
                            HttpMethod.DELETE,
                            "/api/v1/productos/**")
                    .hasRole("ADMIN")

                    // VENTAS
                    .requestMatchers(
                            "/api/v1/ventas/**")
                    .hasAnyRole(
                            "ADMIN",
                            "CLIENTE")

                    // DETALLE VENTA
                    .requestMatchers(
                            "/api/v1/detalleventa/**")
                    .hasAnyRole(
                            "ADMIN",
                            "CLIENTE")

                    // PAGOS
                    .requestMatchers(
                            "/api/v1/pagos/**")
                    .hasAnyRole(
                            "ADMIN",
                            "CLIENTE")

                    // DELIVERY
                    .requestMatchers(
                            "/api/v1/delivery/**")
                    .hasAnyRole(
                            "ADMIN",
                            "CLIENTE")

                    .anyRequest()
                    .authenticated())

            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}