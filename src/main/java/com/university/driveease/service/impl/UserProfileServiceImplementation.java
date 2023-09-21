package com.university.driveease.service.impl;

import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.model.User;
import com.university.driveease.repository.UserRepository;
import com.university.driveease.service.UserProfileService;
import com.university.driveease.util.StringList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImplementation implements UserProfileService {
    private final UserRepository userRepository;
    @Override
    public ResponseDTO getUser(Model model,OidcUser principal) {
        // Retrieve user information from Okta
        String username = principal.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.get();
        model.addAttribute("user", user);
        return ResponseDTO.builder()
                .code(StringList.RSP_SUCCESS)
                .content(user)
                .message("View Profile")
                .build();
    }
}
