package com.example.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.common.VehicleType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "brands")
@Getter
@Setter
public class Brand extends AbstractEntity {
    @Column(name = "name")
    private String name;
    // TODO : chuyển vehicle_type thành varchar và comment @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "type", columnDefinition = "vehicle_type")
    private VehicleType type;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

}