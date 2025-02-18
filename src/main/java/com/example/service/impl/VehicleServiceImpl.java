package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dto.response.VehicleDetailResponse;
import com.example.dto.resquest.VehicleRequestDTO;
import com.example.dto.resquest.VehicleSearchDTO;
import com.example.model.Brand;
import com.example.model.User;
import com.example.model.Vehicle;
import com.example.repository.BrandRepository;
import com.example.repository.UserRepository;
import com.example.repository.VehicleRepository;
import com.example.service.VehicleService;
import com.example.util.mapper.VehicleMapper;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public long saveVehicle(VehicleRequestDTO request) {
        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Vehicle vehicle = vehicleMapper.toEntity(request);
        vehicle.setOwner(owner);
        vehicle.setBrand(brand);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return savedVehicle.getId();
    }

    @Override
    public void updateVehicle(long vehicleId, VehicleRequestDTO request) {
        Vehicle vehicle = getVehicleById(vehicleId);
        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        vehicleMapper.updateEntity(vehicle, request);
        vehicle.setOwner(owner);
        vehicle.setBrand(brand);

        vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public VehicleDetailResponse getVehicle(long vehicleId) {
        Vehicle vehicle = getVehicleById(vehicleId);
        return vehicleMapper.toDto(vehicle);
    }

    @Override
    public Page<VehicleDetailResponse> getAllVehicles(Pageable pageable) {
        Page<Vehicle> page = vehicleRepository.findAll(pageable);
        return vehicleMapper.toDtoPage(page);
    }

    @Override
    public Page<VehicleDetailResponse> searchVehicles(VehicleSearchDTO searchDTO, Pageable pageable) {
        Page<Vehicle> page = vehicleRepository.searchVehicles(
                searchDTO.getBrandId(),
                searchDTO.getBrandName(),
                searchDTO.getManufactureYear(),
                searchDTO.getMinPrice(),
                searchDTO.getMaxPrice(),
                searchDTO.getOwnerId(),
                searchDTO.getOwnerName(),
                pageable
        );
        return vehicleMapper.toDtoPage(page);
    }

    @Override
    public Page<VehicleDetailResponse> findVehiclesBySpecialCondition(Pageable pageable) {
        Page<Vehicle> page = vehicleRepository.findVehiclesByPriceAndBrandCondition(pageable);
        return vehicleMapper.toDtoPage(page);
    }

    private Vehicle getVehicleById(long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }
}
