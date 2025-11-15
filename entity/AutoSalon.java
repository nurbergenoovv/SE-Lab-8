package com.nurbergenovv.lab7.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autosalons")
@Data
public class AutoSalon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "salon")
    private List<Car> availableCars = new ArrayList<>();

}
