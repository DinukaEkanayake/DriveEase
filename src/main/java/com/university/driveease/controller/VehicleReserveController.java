package com.university.driveease.controller;

import com.university.driveease.dto.RequestDTO;
import com.university.driveease.dto.ResponseDTO;
import com.university.driveease.service.VehicleReserveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "api/")
@AllArgsConstructor
public class VehicleReserveController {

    private final VehicleReserveService vehicleReserveService;

    @PostMapping("/saveVehicle")
    public ResponseEntity<ResponseDTO> saveVehicle(@RequestBody RequestDTO request, Principal principal){

        return ResponseEntity.ok().body(
                vehicleReserveService.saveVehicle(request,principal)
        );
    }

}
