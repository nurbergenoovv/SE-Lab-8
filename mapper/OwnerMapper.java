package com.nurbergenovv.lab7.mapper;

import com.nurbergenovv.lab7.dto.CarShortDTO;
import com.nurbergenovv.lab7.dto.OwnerRequestDTO;
import com.nurbergenovv.lab7.dto.OwnerResponseDTO;
import com.nurbergenovv.lab7.entity.Car;
import com.nurbergenovv.lab7.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    @Mapping(target = "cars", expression = "java(toCarShortList(owner.getCars()))")
    OwnerResponseDTO toResponseDto(Owner owner);

    List<OwnerResponseDTO> toResponseDtoList(List<Owner> owners);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    Owner toEntity(OwnerRequestDTO dto);

    List<CarShortDTO> toCarShortList(List<Car> cars);

    CarShortDTO toCarShort(Car car);
}