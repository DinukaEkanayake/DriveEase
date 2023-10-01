package com.university.driveease.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "home";
    }
}

