package com.example.library.config;

import com.example.library.helper.CustomOAuth2SuccessHandler;
import com.example.library.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationSuccessHandler customOAuth2SuccessHandler;

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v3/api/auth/**",
//            "/login/oauth2/**",
//            "/oauth2/**",
//            "/oauth2/authorization/google"
    };

    public SecurityConfig(AuthenticationProvider authenticationProvider,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          AuthenticationSuccessHandler customOAuth2SuccessHandler) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customOAuth2SuccessHandler = customOAuth2SuccessHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .cors(cors -> cors.disable())

                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                });


        http.formLogin(formLogin->{
            formLogin.loginPage("/login");
            formLogin.successForwardUrl("/v3/api/auth/home");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });

        http.oauth2Login(Customizer.withDefaults());
        http.oauth2Login(auth->{
            auth.successHandler(customOAuth2SuccessHandler);
        });

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:9494"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }

//    @Bean
//    public OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService() {
//        // Your custom OAuth2UserService implementation
//        return new CustomOAuth2UserService();
//    }

}
