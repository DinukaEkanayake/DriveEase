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
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/","/getreservations","/profile",
                        "/delete-reservation/{booking_id}","/reservation").authenticated()
                .anyRequest().permitAll();
        http.oauth2Login()
                .loginPage("/oauth2/authorization/asgardeo");
        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(asgardeoClientRegistration());
    }

    private ClientRegistration asgardeoClientRegistration() {
        return ClientRegistration.withRegistrationId("asgardeo")
                .clientId("VvbMfODSJ7kV3VtOxDNxq03gyDMa")
                .clientSecret("6TPN_86It3Kd_YQCG2SYnjEVw7fkdGI5KxTorzYZvToa")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/login/oauth2/code/asgardeo")
                .scope("openid","address","email","phone","profile")
                .authorizationUri("https://api.asgardeo.io/t/org1i56a/oauth2/authorize")
                .tokenUri("https://api.asgardeo.io/t/org1i56a/oauth2/token")
                .userInfoUri("https://api.asgardeo.io/t/org1i56a/oauth2/userinfo")
                .userNameAttributeName("sub")
                .jwkSetUri("https://api.asgardeo.io/t/org1i56a/oauth2/jwks")
                .clientName("asgardeo")
                .build();
    }

}