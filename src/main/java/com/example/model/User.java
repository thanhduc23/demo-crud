package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

}
