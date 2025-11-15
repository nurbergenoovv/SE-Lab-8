package com.nurbergenovv.lab7.service.impl;

import com.nurbergenovv.lab7.dto.CarRequestDTO;
import com.nurbergenovv.lab7.dto.CarResponseDTO;
import com.nurbergenovv.lab7.entity.AutoSalon;
import com.nurbergenovv.lab7.entity.Car;
import com.nurbergenovv.lab7.entity.Owner;
import com.nurbergenovv.lab7.mapper.CarMapper;
import com.nurbergenovv.lab7.repository.AutoSalonRepository;
import com.nurbergenovv.lab7.repository.CarRepository;
import com.nurbergenovv.lab7.repository.OwnerRepository;
import com.nurbergenovv.lab7.service.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final AutoSalonRepository autoSalonRepository;
    private final OwnerRepository ownerRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarResponseDTO> getAll() {
        List<Car> cars = carRepository.findAll();
        return carMapper.toResponseDtoList(cars);
    }

    @Override
    public CarResponseDTO add(CarRequestDTO dto) {
        Car car = carMapper.toEntity(dto);

        if (car.getSalon() != null) {
            AutoSalon salon = autoSalonRepository.findById(dto.getSalonId())
                    .orElseThrow(() -> new RuntimeException("AutoSalon not found with id: " + dto.getSalonId()));
            car.setSalon(salon);
        }

        if (!car.getOwners().isEmpty()) {
            List<Owner> owners = ownerRepository.findAllById(dto.getOwnerIds());
            car.setOwners(owners);
        }

        Car saved = carRepository.save(car);
        return carMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (car.getOwners() != null) {
            List<Owner> owners = new ArrayList<>(car.getOwners());
            for (Owner owner : owners) {
                owner.getCars().remove(car);
            }
            car.getOwners().clear();
        }
        carRepository.delete(car);
        return true;
    }

    @Override
    public CarResponseDTO update(Long id, CarRequestDTO newData) {
        Car existing = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        existing.setModel(newData.getModel());
        existing.setYear(newData.getYear());
        existing.setColor(newData.getColor());
        existing.setPrice(newData.getPrice());

        if (newData.getSalonId() != null) {

            AutoSalon salon = autoSalonRepository.findById(newData.getSalonId())
                    .orElseThrow(() -> new RuntimeException("AutoSalon not found with id: " + newData.getSalonId()));
            existing.setSalon(salon);

        }

        if (!newData.getOwnerIds().isEmpty()) {
            List<Owner> owners = ownerRepository.findAllById(newData.getOwnerIds());
            existing.setOwners(owners);
        }

        Car updated = carRepository.save(existing);
        return carMapper.toResponseDto(updated);
    }

    @Override
    public CarResponseDTO get(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
        return carMapper.toResponseDto(car);
    }
}