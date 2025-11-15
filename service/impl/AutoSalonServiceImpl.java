package com.nurbergenovv.lab7.service.impl;

import com.nurbergenovv.lab7.dto.AutoSalonRequestDTO;
import com.nurbergenovv.lab7.dto.AutoSalonResponseDTO;
import com.nurbergenovv.lab7.entity.AutoSalon;
import com.nurbergenovv.lab7.entity.Car;
import com.nurbergenovv.lab7.mapper.AutoSalonMapper;
import com.nurbergenovv.lab7.repository.AutoSalonRepository;
import com.nurbergenovv.lab7.repository.CarRepository;
import com.nurbergenovv.lab7.service.AutoSalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoSalonServiceImpl implements AutoSalonService {

    private final AutoSalonRepository autoSalonRepository;
    private final CarRepository carRepository;
    private final AutoSalonMapper autoSalonMapper;

    @Override
    public List<AutoSalonResponseDTO> getAll() {
        List<AutoSalon> salons = autoSalonRepository.findAll();
        return autoSalonMapper.toResponseDtoList(salons);
    }

    @Override
    public AutoSalonResponseDTO add(AutoSalonRequestDTO dto) {
        AutoSalon salon = autoSalonMapper.toEntity(dto);

        if (!dto.getCarIds().isEmpty()) {
            List<Car> cars = carRepository.findAllById(dto.getCarIds());
            salon.setAvailableCars(cars);
        }
        AutoSalon saved = autoSalonRepository.save(salon);
        return autoSalonMapper.toResponseDto(saved);
    }

    @Override
    public boolean remove(Long id) {
        if (!autoSalonRepository.existsById(id)) {
            return false;
        }
        autoSalonRepository.deleteById(id);
        return true;
    }

    @Override
    public AutoSalonResponseDTO update(Long id, AutoSalonRequestDTO newData) {
        AutoSalon existing = autoSalonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AutoSalon not found with id: " + id));

        existing.setName(newData.getName());
        existing.setAddress(newData.getAddress());

        if (newData.getCarIds() != null) {
            List<Car> currentCars = existing.getAvailableCars();
            if (currentCars == null) {
                currentCars = new java.util.ArrayList<>();
                existing.setAvailableCars(currentCars);
            }

            currentCars.clear();

            if (!newData.getCarIds().isEmpty()) {
                List<Car> cars = carRepository.findAllById(newData.getCarIds());
                currentCars.addAll(cars);
            }
        }

        AutoSalon updated = autoSalonRepository.save(existing);
        return autoSalonMapper.toResponseDto(updated);
    }

    @Override
    public AutoSalonResponseDTO get(Long id) {
        AutoSalon salon = autoSalonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AutoSalon not found with id: " + id));
        return autoSalonMapper.toResponseDto(salon);
    }
}