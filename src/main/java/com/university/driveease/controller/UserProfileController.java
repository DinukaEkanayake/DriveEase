package com.university.driveease.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@Controller
public class UserProfileController {

    @GetMapping("/profile")
    public String displayUserProfile(@AuthenticationPrincipal OAuth2User oauth2User, Model model){

            String username = oauth2User.getAttribute("username");
            String name = oauth2User.getAttribute("given_name");
            String email = oauth2User.getAttribute("email");
            String contactNo = oauth2User.getAttribute("phone_number");

            // Add user details to the model
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("contactNo", contactNo);

            LinkedHashMap<String, Object> address = oauth2User.getAttribute("address");
            String country = (String) address.get("country");
            model.addAttribute("country", country);

        return "profile";
    }


}

