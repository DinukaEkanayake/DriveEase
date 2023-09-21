package com.university.driveease.service;

import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.model.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;

public interface UserProfileService {

    ResponseDTO getUser(Model model,OidcUser principal);
}
