package com.university.driveease.service;

import com.university.driveease.dto.ResponseDTO;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@Service
public interface UserProfileService {

    ResponseDTO getUser(Model model,OidcUser principal);
}
