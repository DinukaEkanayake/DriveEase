package com.university.driveease.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                //whitelisting-give authorization to some endpoints
                .requestMatchers("/profile", "/reservation").authenticated()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                //.defaultSuccessURL("/profile") // Redirect after successful login
                .and()
                .logout().logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(asgardeoClientRegistration());
    }

    private ClientRegistration asgardeoClientRegistration() {
        return ClientRegistration.withRegistrationId("asgardeo")
                .clientId("icG0fGqggtOebJnKLTBvwmkhmD8a")
                .clientSecret("L9bbdyw2NpynwplENrA6Nx4LYu4tJdp1J8IVzu2YuIAa")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/login/oauth2/code/asgardeo")
                .scope("openid", "email", "profile")
                .authorizationUri("https://api.asgardeo.io/t/org1i56a/oauth2/authorize")
                .tokenUri("https://api.asgardeo.io/t/org1i56a/oauth2/token")
                .userInfoUri("https://api.asgardeo.io/t/org1i56a/oauth2/userinfo")
                .userNameAttributeName("sub")
                .jwkSetUri("https://api.asgardeo.io/t/org1i56a/oauth2/jwks")
                .clientName("Asgardeo")
                .build();
    }
}
