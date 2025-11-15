package com.nurbergenovv.lab7.dto;

import lombok.Data;

import java.util.List;

@Data
public class OwnerResponseDTO {
    private Long id;
    private String fullName;
    private int age;

    private List<CarShortDTO> cars;
}