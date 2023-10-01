package com.university.driveease.controller;

import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(value = "api/")
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/profile")
    public String getUserProfile(OAuth2AuthenticationToken token) {

        ResponseDTO user=userProfileService.getUser(token);

        //Display user information in the view
//        return "Name: " + user.getName() + "<br>Email: " + user.getEmail() +
//                "<br>Contact Number: " + user.getContactNumber() + "<br>Country: " + user.getCountry();

        return "home";
    }


}

