package com.university.driveease.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

	@GetMapping("/")
	public String handleOAuth2Callback(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {
		// Extract user details from the OAuth2User object

		if (oauth2User != null){
			String name = oauth2User.getAttribute("username");
			String email = oauth2User.getAttribute("email");

			// Add user details to the model
			model.addAttribute("oauth2User",oauth2User);
			model.addAttribute("name", name);
			model.addAttribute("email", email);
		}

		return "home";
	}

}