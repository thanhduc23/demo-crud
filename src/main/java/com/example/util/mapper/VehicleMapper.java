package com.example.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.dto.response.VehicleDetailResponse;
import com.example.dto.resquest.VehicleRequestDTO;
import com.example.model.Vehicle;

@Mapper(componentModel = "spring")
public interface VehicleMapper extends BaseMapper<Vehicle, VehicleDetailResponse, VehicleRequestDTO> {

    @Override
    VehicleDetailResponse toDto(Vehicle entity);

    @Override
    Vehicle toEntity(VehicleRequestDTO request);

    @Override
    void updateEntity(@MappingTarget Vehicle entity, VehicleRequestDTO request);
}
