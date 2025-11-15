package com.nurbergenovv.lab7.dto;

import lombok.Data;

import java.util.List;

@Data
public class AutoSalonResponseDTO {
    private Long id;
    private String name;
    private String address;

    private List<CarShortDTO> availableCars;
}