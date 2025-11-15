package com.nurbergenovv.lab7.service;

import com.nurbergenovv.lab7.dto.CarRequestDTO;
import com.nurbergenovv.lab7.dto.CarResponseDTO;

import java.util.List;

public interface CarService {
    List<CarResponseDTO> getAll();

    CarResponseDTO add(CarRequestDTO car);

    boolean remove(Long id);

    CarResponseDTO update(Long id, CarRequestDTO newData);

    CarResponseDTO get(Long id);
}