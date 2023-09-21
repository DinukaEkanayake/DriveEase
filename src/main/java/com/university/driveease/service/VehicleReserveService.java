package com.university.driveease.service;

import com.university.driveease.dto.RequestDTO;
import com.university.driveease.dto.ResponseDTO;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface VehicleReserveService {

    ResponseDTO saveVehicle(RequestDTO request, Principal principal);
}
