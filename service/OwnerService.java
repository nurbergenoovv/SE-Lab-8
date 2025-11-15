package com.nurbergenovv.lab7.service;

import com.nurbergenovv.lab7.dto.OwnerRequestDTO;
import com.nurbergenovv.lab7.dto.OwnerResponseDTO;

import java.util.List;

public interface OwnerService {

    List<OwnerResponseDTO> getAll();

    OwnerResponseDTO add(OwnerRequestDTO owner);

    boolean remove(Long id);

    OwnerResponseDTO update(Long id, OwnerRequestDTO newData);

    OwnerResponseDTO get(Long id);
}