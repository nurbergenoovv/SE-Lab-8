package com.nurbergenovv.lab7.dto;

import com.nurbergenovv.lab7.dto.AutoSalonShortDTO;
import com.nurbergenovv.lab7.dto.OwnerShortDTO;
import lombok.Data;

import java.util.List;

@Data
public class CarResponseDTO {

    private Long id;
    private String model;
    private int year;
    private String color;
    private Float price;

    private AutoSalonShortDTO salon;
    private List<OwnerShortDTO> owners;
}