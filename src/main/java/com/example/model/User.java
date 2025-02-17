package com.example.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
public class User extends AbstractEntity {
    @Column(name="name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

}
