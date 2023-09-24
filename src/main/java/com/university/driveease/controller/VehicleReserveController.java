package com.university.driveease.controller;

import com.university.driveease.dto.RequestDTO;
import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.model.Vehicle;
import com.university.driveease.service.VehicleReserveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/")
@AllArgsConstructor
public class VehicleReserveController {

    private final VehicleReserveService vehicleReserveService;

    @PostMapping("/reservation")
    public String saveReservation(Vehicle reservation, OAuth2AuthenticationToken token) {
        // Retrieve the authenticated user's username from the token
        String username = token.getName();
        String profile=vehicleReserveService.saveReservation(reservation,username);
        // Save the reservation to the database along with the username

        return profile; // Redirect to the profile page
    }

    @PostMapping("/delete-reservation/{reservationId}")
    public String deleteReservation(@PathVariable Long reservationId) {
        // Implement reservation deletion based on the reservationId
        vehicleReserveService.deleteReservation(reservationId);

        return "redirect:/profile"; // Redirect to the profile page
    }



}
