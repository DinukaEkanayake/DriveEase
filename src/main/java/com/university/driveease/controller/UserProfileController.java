package com.university.driveease.controller;

import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.model.User;
import com.university.driveease.repository.UserRepository;
import com.university.driveease.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
    public ResponseEntity<ResponseDTO> addNewDelivery(Model model, @AuthenticationPrincipal OidcUser principal){

        return ResponseEntity.ok().body(
                userProfileService.getUser(model,principal) // Render the profile view
        );
    }

}

