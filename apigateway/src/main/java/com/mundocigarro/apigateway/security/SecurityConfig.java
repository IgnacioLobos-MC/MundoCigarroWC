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

                    // Login libre
                    .requestMatchers(
                            "/api/v1/auth/**")
                    .permitAll()

                    // Solo ADMIN
                    .requestMatchers(
                            "/api/v1/proveedores/**")
                    .hasRole("ADMIN")

                    .requestMatchers(
                            "/api/v1/inventario/**")
                    .hasRole("ADMIN")

                    // Productos: todos pueden ver
                    .requestMatchers(
                            HttpMethod.GET,
                            "/api/v1/productos/**")
                    .hasAnyRole(
                            "ADMIN",
                            "CLIENTE")

                    // Productos: solo ADMIN modifica
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

                    // Ventas
                    .requestMatchers(
                            "/api/v1/ventas/**")
                    .hasAnyRole(
                            "ADMIN",
                            "CLIENTE")

                    // Delivery
                    .requestMatchers(
                            "/api/v1/delivery/**")
                    .hasAnyRole(
                            "ADMIN",
                            "CLIENTE")

                    // Pagos
                    .requestMatchers(
                            "/api/v1/pagos/**")
                    .hasAnyRole(
                            "ADMIN",
                            "CLIENTE")

                    // Consultar rutas
                    .requestMatchers(
                        "/api/v1/rutas/**")
                    .hasAnyRole(
                        "ADMIN",
                        "CLIENTE")

                     // Conductores
                     .requestMatchers(
                             HttpMethod.GET,
                        "/api/v1/conductores/**")
                     .hasAnyRole(
                        "ADMIN",
                        "CLIENTE")

                      .requestMatchers(
                              HttpMethod.POST,
                        "/api/v1/conductores/**")
                      .hasRole("ADMIN")

                      .requestMatchers(
                              HttpMethod.PUT,
                        "/api/v1/conductores/**")
                      .hasRole("ADMIN")

                      .requestMatchers(
                              HttpMethod.DELETE,
                        "/api/v1/conductores/**")
                      .hasRole("ADMIN")


                     // Vehículos
                     .requestMatchers(
                             HttpMethod.GET,
                     "/api/v1/vehiculos/**")
                     .hasAnyRole(
                        "ADMIN",
                        "CLIENTE")

                     .requestMatchers(
                             HttpMethod.POST,
                     "/api/v1/vehiculos/**")
                     .hasRole("ADMIN")

                     .requestMatchers(
                             HttpMethod.PUT,
                     "/api/v1/vehiculos/**")
                     .hasRole("ADMIN")

                     .requestMatchers(
                             HttpMethod.DELETE,
                     "/api/v1/vehiculos/**")
                     .hasRole("ADMIN")

                    .anyRequest()
                    .authenticated())

            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}