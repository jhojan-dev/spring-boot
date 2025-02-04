package com.company.books.backend.config;

import com.company.books.backend.filter.JwtReqFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class ConfigSecurity {

    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter;

    /*
        * Usuarios en memoria
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails jhojan = User.builder()
                .username("jhojan")
                .password("{noop}jhojan2025")
                .roles("Empleado")
                .build();

        UserDetails camila = User.builder()
                .username("camila")
                .password("{noop}camila2025")
                .roles("Empleado", "Jefe")
                .build();

        UserDetails ana = User.builder()
                .username("ana")
                .password("{noop}ana2025")
                .roles("Empleado", "Jefe")
                .build();

        return new InMemoryUserDetailsManager(jhojan, camila, ana);
    }
     */

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configure -> configure
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**", "/v1/authenticate").permitAll() // Rutas públicas
                        .requestMatchers(HttpMethod.GET, "/v1/libros").hasRole("Empleado")
                        .requestMatchers(HttpMethod.GET, "/v1/libros/**").hasRole("Empleado")
                        .requestMatchers(HttpMethod.POST, "/v1/libros").hasRole("Jefe")
                        .requestMatchers(HttpMethod.PUT, "/v1/libros/**").hasRole("Jefe")
                        .requestMatchers(HttpMethod.DELETE, "/v1/libros/**").hasRole("Jefe")
                        .requestMatchers(HttpMethod.GET, "/v1/categorias").hasRole("Empleado")
                        .requestMatchers(HttpMethod.GET, "/v1/categorias/**").hasRole("Empleado")
                        .requestMatchers(HttpMethod.POST, "/v1/categorias").hasRole("Jefe")
                        .requestMatchers(HttpMethod.PUT, "/v1/categorias/**").hasRole("Jefe")
                        .requestMatchers(HttpMethod.DELETE, "/v1/categorias/**").hasRole("Jefe")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
