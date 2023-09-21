package com.university.driveease.service.impl;

import com.university.driveease.dto.RequestDTO;
import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.model.Vehicle;
import com.university.driveease.repository.VehicleRepository;
import com.university.driveease.service.VehicleReserveService;
import com.university.driveease.util.StringList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class VehicleReserveServiceImplementation implements VehicleReserveService {

    private final VehicleRepository vehicleRepository;
    @Override
    public ResponseDTO saveVehicle(RequestDTO request, Principal principal) {

        if (principal instanceof OAuth2AuthenticationToken oauthToken) {
            String name = oauthToken.getName(); // User's name
            String email = oauthToken.getPrincipal().getAttribute("email"); // User's email (adjust the attribute name as needed)
            String contactNumber = oauthToken.getPrincipal().getAttribute("contactNumber"); // User's contact number (adjust the attribute name as needed)
            // You can access other user attributes in a similar manner

            var vehicle = Vehicle.builder()
                    .name(name)
                    .email(email)
                    .phone(contactNumber)
                    .date(request.getDate())
                    .time(request.getTime())
                    .location(request.getLocation())
                    .vehicle_no(request.getVehicle_no())
                    .mileage(request.getMileage())
                    .message(request.getMessage())
                    .build();

            var savedVehicle=vehicleRepository.save(vehicle);

            return ResponseDTO.builder()
                    .code(StringList.RSP_SUCCESS)
                    .content(savedVehicle)
                    .message("Vehicle reserved")
                    .build();
        }

        // Handle non-OAuth2 authentication (e.g., custom authentication)
        return ResponseDTO.builder()
                .code(StringList.RSP_ERROR)
                .message("User profile not available")
                .build();

    }
}
