package com.university.driveease.config;//package com.university.driveease.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("/login/oauth2/code/asgardeo")
    private String loginProcessPath;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                //whitelisting-give authorization to some endpoints
                .requestMatchers("/secured").authenticated()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .loginProcessingUrl(loginProcessPath)
                .loginPage("/oauth2/authorization/asgardeo")
                .defaultSuccessUrl("/login/oauth2/code/asgardeo") // Redirect after successful login
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

    @Bean
    UserDetailsService users(@Autowired PasswordEncoder pwEnc) {
        UserDetails user = User.builder()
                .username("user")
                .password(pwEnc.encode("top"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}