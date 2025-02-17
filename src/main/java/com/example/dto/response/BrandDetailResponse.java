package com.example.dto.response;

import com.example.common.VehicleType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BrandDetailResponse {
    private Long id;
    private String name;
    private VehicleType type;
}
