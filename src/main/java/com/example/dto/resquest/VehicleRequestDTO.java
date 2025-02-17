package com.example.dto.resquest;

import lombok.Getter;

@Getter
public class VehicleRequestDTO {
    private String name;
    private Integer manufactureYear;
    private Double price;
    private Long ownerId;
    private Long brandId;
}
