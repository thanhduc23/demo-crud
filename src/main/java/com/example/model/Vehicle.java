package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "vehicles")
@Getter
@Setter
public class Vehicle extends AbstractEntity {

    @Column(name = "name")
    private String name;
    
    @Column(name = "manufacture_year")
    private Integer manufactureYear;
    
    @Column(name = "price")
    private Double price;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    
    
}
