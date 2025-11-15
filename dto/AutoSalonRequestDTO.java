package com.nurbergenovv.lab7.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AutoSalonRequestDTO {
    private String name;
    private String address;

    private List<Long> CarIds = new ArrayList<>();
}