package com.nurbergenovv.lab7.service;

import com.nurbergenovv.lab7.dto.AutoSalonRequestDTO;
import com.nurbergenovv.lab7.dto.AutoSalonResponseDTO;

import java.util.List;

public interface AutoSalonService {

    List<AutoSalonResponseDTO> getAll();

    AutoSalonResponseDTO add(AutoSalonRequestDTO salon);

    boolean remove(Long id);

    AutoSalonResponseDTO update(Long id, AutoSalonRequestDTO newData);

    AutoSalonResponseDTO get(Long id);
}