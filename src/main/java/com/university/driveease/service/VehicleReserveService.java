package com.university.driveease.service;

import com.university.driveease.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleReserveService {
    String saveReservation(Vehicle reservation, String username);
    void deleteReservation(Long reservationId);
    List<Vehicle> getAllReservationsByUsername(String username);
}
