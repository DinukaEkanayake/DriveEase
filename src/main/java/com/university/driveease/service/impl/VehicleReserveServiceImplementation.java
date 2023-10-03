package com.university.driveease.service.impl;

import com.university.driveease.model.Vehicle;
import com.university.driveease.repository.VehicleRepository;
import com.university.driveease.service.VehicleReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleReserveServiceImplementation implements VehicleReserveService {

    private final VehicleRepository vehicleRepository;

    @Override
    public String saveReservation(Vehicle reservation, String username) {

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

        return "reservations";
    }

    @Override
    public void deleteReservation(Long reservationId) {
        vehicleRepository.deleteById(reservationId);
    }

    @Override
    public List<Vehicle> getAllReservationsByUsername(String username) {
        return vehicleRepository.findAllByUsername(username);
    }

}
