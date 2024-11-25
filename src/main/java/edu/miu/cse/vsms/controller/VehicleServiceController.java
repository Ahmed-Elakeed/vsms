package edu.miu.cse.vsms.controller;

import edu.miu.cse.vsms.dto.request.ServiceRequestDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class VehicleServiceController {
    
    private final VehicleService vehicleService;

    // Assign a service to an employee
    @PostMapping
    public ResponseEntity<VehicleServiceResponseDto> assignService(@RequestBody ServiceRequestDto request) {
        VehicleServiceResponseDto vehicleServiceResponseDto = vehicleService.assignService(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vehicleServiceResponseDto.id()).toUri();
        return ResponseEntity.created(location).body(vehicleServiceResponseDto);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<VehicleServiceResponseDto> getService(@PathVariable long id) {
        // Supposing that there is an endpoint that returns Vehicle Service by ID
        return ResponseEntity.ok(null);
    }
    
}
