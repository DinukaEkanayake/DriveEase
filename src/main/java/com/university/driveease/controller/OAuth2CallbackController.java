package com.university.driveease.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//to handle auth2 callback
@RestController
public class OAuth2CallbackController {

    @GetMapping("/login/oauth2/code/asgardeo")
    public ModelAndView defaultPage(@AuthenticationPrincipal OidcUser user) {
        ModelAndView nextPage = new ModelAndView("login");
        nextPage.addObject("user", user);
        String username = user.getAttribute("username");
        String email = user.getEmail();
        return nextPage;
    }

}
