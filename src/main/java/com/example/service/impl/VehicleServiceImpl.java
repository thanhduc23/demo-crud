package com.example.service.impl;

import com.example.dto.response.PageResponse;
import com.example.dto.response.VehicleDetailResponse;
import com.example.dto.resquest.VehicleRequestDTO;
import com.example.dto.resquest.VehicleSearchDTO;
import com.example.model.Vehicle;
import com.example.model.User;
import com.example.model.Brand;
import com.example.repository.VehicleRepository;
import com.example.repository.UserRepository;
import com.example.repository.BrandRepository;
import com.example.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public long saveVehicle(VehicleRequestDTO request) {
        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Vehicle vehicle = new Vehicle();
        vehicle.setName(request.getName());
        vehicle.setManufactureYear(request.getManufactureYear());
        vehicle.setPrice(request.getPrice());
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

        vehicle.setName(request.getName());
        vehicle.setManufactureYear(request.getManufactureYear());
        vehicle.setPrice(request.getPrice());
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
        return buildVehicleResponse(vehicle);
    }

    @Override
    public PageResponse getAllVehicles(int pageNo, int pageSize) {
        Page<Vehicle> page = vehicleRepository.findAll(PageRequest.of(pageNo, pageSize));
        
        List<VehicleDetailResponse> list = page.stream()
                .map(this::buildVehicleResponse)
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public PageResponse searchVehicles(VehicleSearchDTO searchDTO, int pageNo, int pageSize) {
        Page<Vehicle> page = vehicleRepository.searchVehicles(
                searchDTO.getBrandId(),
                searchDTO.getBrandName(),
                searchDTO.getManufactureYear(),
                searchDTO.getMinPrice(),
                searchDTO.getMaxPrice(),
                searchDTO.getOwnerId(),
                searchDTO.getOwnerName(),
                PageRequest.of(pageNo, pageSize)
        );
        
        List<VehicleDetailResponse> list = page.stream()
                .map(this::buildVehicleResponse)
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public PageResponse findVehiclesBySpecialCondition(int pageNo, int pageSize) {
        Page<Vehicle> page = vehicleRepository.findVehiclesByPriceAndBrandCondition(
            PageRequest.of(pageNo, pageSize)
        );
        
        List<VehicleDetailResponse> list = page.stream()
                .map(this::buildVehicleResponse)
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(list)
                .build();
    }

    private Vehicle getVehicleById(long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    private VehicleDetailResponse buildVehicleResponse(Vehicle vehicle) {
        return VehicleDetailResponse.builder()
                .id(vehicle.getId())
                .name(vehicle.getName())
                .manufactureYear(vehicle.getManufactureYear())
                .price(vehicle.getPrice())
                .ownerName(vehicle.getOwner().getName())
                .brandName(vehicle.getBrand().getName())
                .build();
    }
}
