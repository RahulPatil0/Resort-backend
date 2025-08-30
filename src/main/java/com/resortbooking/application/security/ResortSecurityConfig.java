package com.resortbooking.application.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resortbooking.application.filter.JWTTokenValidatorFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResortSecurityConfig {

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
            // stateless APIs
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // CORS
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:8080"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setMaxAge(3600L);
                return config;
            }))

            // Disable CSRF for APIs
            .csrf(csrf -> csrf.disable())

            // Add JWT validator
            .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)

            // ---- AUTHORIZATION RULES ----
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/users/signup").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users/signin").permitAll() // <-- allow signin
                .requestMatchers(HttpMethod.GET, "/api/users/email").permitAll()
                .requestMatchers(
                    "/contact",
                    "/validator",
                    "/user/signInWithGoogle",
                    "/validator/verify",
                    "/products/getAll",
                    "/api/auth/request-otp",
                    "/api/auth/verify-otp",
                    "/api/auth/register",
                    "/api/auth/login",
                    "/api/roles",
                    "/oauth2/authorization/**",
                    "/login/oauth2/code/**"
                ).permitAll()
                .anyRequest().authenticated()
            )

            // Keep OAuth2 login
            .oauth2Login(oauth2 -> oauth2.successHandler(oAuth2SuccessHandler()))

            // Exception handling
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAuthorizationEntryPoint())
            );

        return http.build();
    }

    /**
     * OAuth2 Success Handler (Google login)
     */
    @Bean
    public AuthenticationSuccessHandler oAuth2SuccessHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> data = new HashMap<>();
            data.put("message", "Login successful");

            if (authentication.getPrincipal() instanceof OAuth2User oAuth2User) {
                data.put("name", oAuth2User.getAttribute("name"));
                data.put("email", oAuth2User.getAttribute("email"));
                data.put("picture", oAuth2User.getAttribute("picture"));
            }

            // TODO: Replace with real JWT generation
            data.put("token", "dummy-jwt-token");

            new ObjectMapper().writeValue(response.getWriter(), data);
        };
    }
}
