package com.university.driveease.controller;

import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.model.User;
import com.university.driveease.repository.UserRepository;
import com.university.driveease.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/")
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/profile")
    public String getUserProfile(OAuth2AuthenticationToken token) {
        OAuth2User user = token.getPrincipal();
        String name = user.getAttribute("name");
        String email = user.getAttribute("email");
        String contactNumber = user.getAttribute("contactNumber");
        String country = user.getAttribute("country");

        // Display user information in the view
        return "Name: " + name + "<br>Email: " + email + "<br>Contact Number: " + contactNumber + "<br>Country: " + country;
    }


}

