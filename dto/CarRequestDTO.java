package com.nurbergenovv.lab7.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarRequestDTO {

    private String model;
    private int year;
    private String color;
    private Float price;

    private Long salonId = null;
    private List<Long> OwnerIds = new ArrayList<>();
}