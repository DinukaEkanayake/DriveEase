package com.university.driveease.service.impl;

import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImplementation implements UserProfileService {

    @Override
    public ResponseDTO getUser(OAuth2AuthenticationToken token) {

        OAuth2User user = token.getPrincipal();
        String name = user.getAttribute("name");
        String email = user.getAttribute("email");
        String contactNumber = user.getAttribute("contactNumber");
        String country = user.getAttribute("country");

        return ResponseDTO.builder()
                .name(name)
                .email(email)
                .contactNumber(contactNumber)
                .country(country)
                .build();

    }

}
