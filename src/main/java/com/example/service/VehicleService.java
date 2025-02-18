package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dto.response.VehicleDetailResponse;
import com.example.dto.resquest.VehicleRequestDTO;
import com.example.dto.resquest.VehicleSearchDTO;

public interface VehicleService {

    long saveVehicle(VehicleRequestDTO request);

    void updateVehicle(long vehicleId, VehicleRequestDTO request);

    void deleteVehicle(long vehicleId);

    VehicleDetailResponse getVehicle(long vehicleId);

    Page<VehicleDetailResponse> getAllVehicles(Pageable pageable);

    Page<VehicleDetailResponse> searchVehicles(VehicleSearchDTO searchDTO, Pageable pageable);

    Page<VehicleDetailResponse> findVehiclesBySpecialCondition(Pageable pageable);
}
