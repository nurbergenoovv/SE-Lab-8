package com.nurbergenovv.lab7.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private int year;
    private String color;
    private Float price;

    @ManyToOne()
    @JoinColumn(name = "salon_id")
    private AutoSalon salon;


    @ManyToMany(mappedBy = "cars")
    private List<Owner> owners = new ArrayList<>();

}
