package com.example.dto.resquest;

import com.example.common.VehicleType;

import lombok.Getter;

@Getter
public class BrandRequestDTO {
    private String name;
    private VehicleType type;
}
