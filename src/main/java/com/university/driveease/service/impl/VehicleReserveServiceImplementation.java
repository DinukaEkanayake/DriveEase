package com.university.driveease.service.impl;

import com.university.driveease.dto.RequestDTO;
import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.model.Vehicle;
import com.university.driveease.repository.VehicleRepository;
import com.university.driveease.service.VehicleReserveService;
import com.university.driveease.util.StringList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleReserveServiceImplementation implements VehicleReserveService {

    private final VehicleRepository vehicleRepository;

    @Override
    public String saveReservation(Vehicle reservation,String username) {

        var vehicle_reservation = Vehicle.builder()
                .date(reservation.getDate())
                .time(reservation.getTime())
                .location(reservation.getLocation())
                .vehicle_no(reservation.getVehicle_no())
                .mileage(reservation.getMileage())
                .message(reservation.getMessage())
                .username(username)
                .build();

        vehicleRepository.save(vehicle_reservation);

        return "redirect:/profile";
    }

}
