package com.mycompany.hrs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    private final AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    public SecurityConfig(CustomUserDetailsService uds, JwtFilter jwtFilter, AuthEntryPointJwt unauthorizedHandler) {
        this.userDetailsService = uds;
        this.jwtFilter = jwtFilter;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
        return auth.build();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Updated configuration for Spring Security 6.x
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .exceptionHandling(ex -> ex
                    .authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

            // 0) let the async‐dispatch through, so your JWT header is honored again
          .dispatcherTypeMatchers(DispatcherType.ASYNC).permitAll()
          
                    // 1) public endpoints: login, token refresh, etc.
                    .requestMatchers("/api/auth/**").permitAll()

                    // 2) read‐only endpoints: any authenticated user
                    .requestMatchers(HttpMethod.GET, "/api/departments/**")
                    .hasAnyAuthority("ADMIN", "USER", "HR")

                    // 3) ADMIN‐only writes (POST/PUT/DELETE)
                    // .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                    // .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                    // .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")

                    // 4) any other /api/** must still be authenticated
                    //.requestMatchers("/api/**").authenticated()

                    // 5) all other (Thymeleaf pages, static) are public
                    .anyRequest().permitAll())

            // 6) plug in your JWT filter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
