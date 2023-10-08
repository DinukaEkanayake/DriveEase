package com.university.driveease.controller;

import com.university.driveease.model.Vehicle;
import com.university.driveease.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class VehicleReserveController {

    private final VehicleRepository vehicleRepository;

    @GetMapping("/reservation")
    public String getReservation(Model model) {

        Vehicle vehicle = new Vehicle();

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("date",vehicle.getDate());
        model.addAttribute("time",vehicle.getTime());
        model.addAttribute("location",vehicle.getLocation());
        model.addAttribute("vehicle_no",vehicle.getVehicle_no());
        model.addAttribute("mileage",vehicle.getMileage());
        model.addAttribute("message",vehicle.getMessage());


        return "vehicleReservation";

    }
    @PostMapping("/reservation")
    public String saveReservation(@ModelAttribute("vehicle") Vehicle form, @AuthenticationPrincipal OAuth2User oauth2User, Model model) {

                // Retrieve the authenticated user's username from the token
                String username = oauth2User.getAttribute("username");
                var vehicle_reservation = Vehicle.builder()
                        .date(form.getDate())
                        .time(form.getTime())
                        .location(form.getLocation())
                        .vehicle_no(form.getVehicle_no())
                        .mileage(form.getMileage())
                        .message(form.getMessage())
                        .username(username)
                        .build();

                // Add user details to the model
                model.addAttribute("reserved_date",vehicle_reservation.getDate());
                model.addAttribute("reserved_time",vehicle_reservation.getTime());
                model.addAttribute("reserved_location",vehicle_reservation.getLocation());
                model.addAttribute("reserved_vehicle_no",vehicle_reservation.getVehicle_no());
                model.addAttribute("reserved_mileage",vehicle_reservation.getMileage());
                model.addAttribute("reserved_message",vehicle_reservation.getMessage());
                model.addAttribute("reserved_username",vehicle_reservation.getUsername());

                vehicleRepository.save(vehicle_reservation);

                return "success";

    }

    @PostMapping("/delete-reservation/{booking_id}")
    public String deleteReservation(@PathVariable("booking_id") Long id,Model model,OAuth2AuthenticationToken token) {

        vehicleRepository.deleteById(id);
        List<Vehicle> reservations = vehicleRepository.findAllByUsername(token.getName());
        model.addAttribute("reservations", reservations);

        return "reservations";
    }

    @GetMapping("/getreservations")
    public String viewAllReservations(Model model,@AuthenticationPrincipal OAuth2User oauth2User) {
        List<Vehicle> reservations = vehicleRepository.findAllByUsername(oauth2User.getAttribute("username"));
        model.addAttribute("reservations", reservations);
        return "reservations";
    }


}
