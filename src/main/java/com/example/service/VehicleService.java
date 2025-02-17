package com.example.service;

import com.example.dto.response.PageResponse;
import com.example.dto.response.VehicleDetailResponse;
import com.example.dto.resquest.VehicleRequestDTO;
import com.example.dto.resquest.VehicleSearchDTO;

public interface VehicleService {
    long saveVehicle(VehicleRequestDTO request);
    void updateVehicle(long vehicleId, VehicleRequestDTO request);
    void deleteVehicle(long vehicleId);
    VehicleDetailResponse getVehicle(long vehicleId);
    PageResponse getAllVehicles(int pageNo, int pageSize);
    PageResponse searchVehicles(VehicleSearchDTO searchDTO, int pageNo, int pageSize);
    PageResponse findVehiclesBySpecialCondition(int pageNo, int pageSize);
}
