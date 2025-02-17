package com.example.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VehicleDetailResponse {
    private Long id;
    private String name;
    private Integer manufactureYear;
    private Double price;
    private String ownerName;
    private String brandName;
}
