package com.university.driveease.controller;

import com.university.driveease.model.Vehicle;
import com.university.driveease.repository.VehicleRepository;
import com.university.driveease.service.VehicleReserveService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class VehicleReserveController {

    private final VehicleReserveService vehicleReserveService;
    private final VehicleRepository vehicleRepository;

    @GetMapping("/reservation")
    public String getReservation(Model model) {

        Vehicle vehicle = new Vehicle();

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("date",vehicle.getDate());
        //vehicle.setTime(LocalTime.now()); // Set the time with a LocalTime object
        model.addAttribute("time",vehicle.getTime());
        model.addAttribute("location",vehicle.getLocation());
        model.addAttribute("vehicle_no",vehicle.getVehicle_no());
        model.addAttribute("mileage",vehicle.getMileage());
        model.addAttribute("message",vehicle.getMessage());


        return "vehicleReservation";

    }
    @PostMapping("/reservation")
    public String saveReservation(@ModelAttribute("vehicle") Vehicle form, OAuth2AuthenticationToken token) {

            if(vehicleRepository.findByVehicle_no(form.getVehicle_no()).isPresent()){
                return "error";
            }
            else {
                // Retrieve the authenticated user's username from the token
                String username = token.getName();
                // Save the reservation to the database along with the username

                return vehicleReserveService.saveReservation(form, username);
            }

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
