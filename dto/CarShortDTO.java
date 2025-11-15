package com.nurbergenovv.lab7.dto;

import lombok.Data;

@Data
public class CarShortDTO {
    private Long id;
    private String model;
    private int year;
    private String color;
    private Float price;
}