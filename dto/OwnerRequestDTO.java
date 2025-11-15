package com.nurbergenovv.lab7.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OwnerRequestDTO {
    private String fullName;
    private int age;

    private List<Long> carIds = new ArrayList<>();
}