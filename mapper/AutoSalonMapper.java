package com.nurbergenovv.lab7.mapper;

import com.nurbergenovv.lab7.dto.AutoSalonRequestDTO;
import com.nurbergenovv.lab7.dto.AutoSalonResponseDTO;
import com.nurbergenovv.lab7.dto.CarShortDTO;
import com.nurbergenovv.lab7.entity.AutoSalon;
import com.nurbergenovv.lab7.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AutoSalonMapper {

    @Mapping(target = "availableCars", expression = "java(toCarShortList(salon.getAvailableCars()))")
    AutoSalonResponseDTO toResponseDto(AutoSalon salon);

    List<AutoSalonResponseDTO> toResponseDtoList(List<AutoSalon> salons);

    @Mapping(target = "id", ignore = true)
    AutoSalon toEntity(AutoSalonRequestDTO dto);

    List<CarShortDTO> toCarShortList(List<Car> cars);

    CarShortDTO toCarShort(Car car);
}