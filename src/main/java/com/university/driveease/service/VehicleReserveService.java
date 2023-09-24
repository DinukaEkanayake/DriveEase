package com.university.driveease.service;

import com.university.driveease.dto.RequestDTO;
import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.model.Vehicle;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface VehicleReserveService {

    //ResponseDTO saveVehicle(RequestDTO request, Principal principal);

    String saveReservation(Vehicle reservation,String username);
}
