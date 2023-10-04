package com.university.driveease.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController {

	@GetMapping("/")
	public String displayHomePage(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {

		model.addAttribute("oauth2User",oauth2User);
		return "home";
	}

}