package com.example.dto.resquest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleSearchDTO {
    private Long brandId;
    private Integer manufactureYear;
    private Double minPrice;
    private Double maxPrice;
    private Long ownerId;
    private String ownerName;
    private String brandName;
}
