package com.university.driveease.controller;

import com.university.driveease.model.Vehicle;
import com.university.driveease.service.VehicleReserveService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getreservations")
    public String viewAllReservations(Model model, OAuth2AuthenticationToken token) {
        String username = token.getName();
        List<Vehicle> reservations = vehicleReserveService.getAllReservationsByUsername(username);
        model.addAttribute("reservations", reservations);
        return "reservations"; // Display the reservations in a view
    }




}
