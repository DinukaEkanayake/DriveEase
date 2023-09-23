package com.university.driveease.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/")
@AllArgsConstructor
public class AppController {

    @GetMapping("/")
    public String currentUserName(Model model, Authentication authentication) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
        model.addAttribute("userName", userDetails.getName());
        model.addAttribute("IDTokenClaims", userDetails);
        return "home";
    }

}