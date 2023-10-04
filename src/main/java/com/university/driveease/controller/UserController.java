package com.university.driveease.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;


@Controller
public class UserController {

	@GetMapping("/")
	public String handleOAuth2Callback(@AuthenticationPrincipal OAuth2User oauth2User, Model model) throws JsonProcessingException {

		if (oauth2User != null){
			String username = oauth2User.getAttribute("username");
			String name = oauth2User.getAttribute("given_name");
			String email = oauth2User.getAttribute("email");
			String contactNo = oauth2User.getAttribute("phone_number");

			// Add user details to the model
			model.addAttribute("oauth2User",oauth2User);
			model.addAttribute("username", username);
			model.addAttribute("name", name);
			model.addAttribute("email", email);
			model.addAttribute("contactNo", contactNo);

			LinkedHashMap<String, Object> address = oauth2User.getAttribute("address");
			String country = (String) address.get("country");
			model.addAttribute("country", country);
		}

		return "home";
	}

}