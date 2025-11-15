package com.nurbergenovv.lab7.service.impl;

import com.nurbergenovv.lab7.dto.OwnerRequestDTO;
import com.nurbergenovv.lab7.dto.OwnerResponseDTO;
import com.nurbergenovv.lab7.entity.Car;
import com.nurbergenovv.lab7.entity.Owner;
import com.nurbergenovv.lab7.mapper.OwnerMapper;
import com.nurbergenovv.lab7.repository.CarRepository;
import com.nurbergenovv.lab7.repository.OwnerRepository;
import com.nurbergenovv.lab7.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final CarRepository carRepository;
    private final OwnerMapper ownerMapper;

    @Override
    public List<OwnerResponseDTO> getAll() {
        List<Owner> owners = ownerRepository.findAll();
        return ownerMapper.toResponseDtoList(owners);
    }

    @Override
    public OwnerResponseDTO add(OwnerRequestDTO dto) {
        Owner owner = ownerMapper.toEntity(dto);

        if (!dto.getCarIds().isEmpty()) {
            List<Car> cars = carRepository.findAllById(dto.getCarIds());
            owner.setCars(cars);
        }

        Owner saved = ownerRepository.save(owner);
        return ownerMapper.toResponseDto(saved);
    }

    @Override
    public boolean remove(Long id) {
        if (!ownerRepository.existsById(id)) {
            return false;
        }
        ownerRepository.deleteById(id);
        return true;
    }

    @Override
    public OwnerResponseDTO update(Long id, OwnerRequestDTO newData) {
        Owner existing = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));

        existing.setFullName(newData.getFullName());
        existing.setAge(newData.getAge());

        if (!newData.getCarIds().isEmpty()) {
            List<Car> cars = carRepository.findAllById(newData.getCarIds());
            existing.setCars(cars);
        }

        Owner updated = ownerRepository.save(existing);
        return ownerMapper.toResponseDto(updated);
    }

    @Override
    public OwnerResponseDTO get(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));
        return ownerMapper.toResponseDto(owner);
    }
}