package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.response.ResponseData;
import com.example.dto.response.VehicleDetailResponse;
import com.example.dto.resquest.VehicleRequestDTO;
import com.example.dto.resquest.VehicleSearchDTO;
import com.example.service.VehicleService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/vehicle")
@Tag(name = "Vehicle Controller")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/")
    public ResponseData<Long> addVehicle(@RequestBody VehicleRequestDTO request) {
        long vehicleId = vehicleService.saveVehicle(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Vehicle created successfully", vehicleId);
    }

    @GetMapping("/")
    public ResponseData<Page<VehicleDetailResponse>> getAllVehicles(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        Page<VehicleDetailResponse> response = vehicleService.getAllVehicles(pageable);
        return new ResponseData<>(HttpStatus.OK.value(), "vehicles", response);
    }

    @GetMapping("/{vehicleId}")
    public ResponseData<VehicleDetailResponse> getVehicleDetails(@PathVariable long vehicleId) {
        VehicleDetailResponse response = vehicleService.getVehicle(vehicleId);
        return new ResponseData<>(HttpStatus.OK.value(), "vehicle", response);
    }

    @PutMapping("/{vehicleId}")
    public ResponseData<Void> updateVehicle(@PathVariable long vehicleId, @RequestBody VehicleRequestDTO request) {
        vehicleService.updateVehicle(vehicleId, request);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Vehicle updated successfully");
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseData<Void> deleteVehicle(@PathVariable long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Vehicle deleted successfully");
    }

    @PostMapping("/search")
    public ResponseData<Page<VehicleDetailResponse>> searchVehicles(
            @RequestBody VehicleSearchDTO searchDTO,
            @PageableDefault(page = 0, size = 20) Pageable pageable) {
        Page<VehicleDetailResponse> response = vehicleService.searchVehicles(searchDTO, pageable);
        return new ResponseData<>(HttpStatus.OK.value(), "vehicles", response);
    }

    @GetMapping("/special-condition")
    public ResponseData<Page<VehicleDetailResponse>> getVehiclesBySpecialCondition(
            @PageableDefault(page = 0, size = 20) Pageable pageable) {
        Page<VehicleDetailResponse> response = vehicleService.findVehiclesBySpecialCondition(pageable);
        return new ResponseData<>(HttpStatus.OK.value(), "vehicles", response);
    }
}
