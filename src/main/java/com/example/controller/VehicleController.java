package com.example.controller;

import com.example.dto.response.PageResponse;
import com.example.dto.response.ResponseData;
import com.example.dto.response.VehicleDetailResponse;
import com.example.dto.resquest.VehicleRequestDTO;
import com.example.dto.resquest.VehicleSearchDTO;
import com.example.service.VehicleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public ResponseData<PageResponse> getAllVehicles(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "20", required = false) int pageSize) {
        PageResponse response = vehicleService.getAllVehicles(pageNo, pageSize);
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
    public ResponseData<PageResponse> searchVehicles(
            @RequestBody VehicleSearchDTO searchDTO,
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "20", required = false) int pageSize) {
        PageResponse response = vehicleService.searchVehicles(searchDTO, pageNo, pageSize);
        return new ResponseData<>(HttpStatus.OK.value(), "vehicles", response);
    }

    @GetMapping("/special-condition")
    public ResponseData<PageResponse> getVehiclesBySpecialCondition(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "20", required = false) int pageSize) {
        PageResponse response = vehicleService.findVehiclesBySpecialCondition(pageNo, pageSize);
        return new ResponseData<>(HttpStatus.OK.value(), "vehicles", response);
    }
}
