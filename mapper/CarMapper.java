package com.nurbergenovv.lab7.mapper;

import com.nurbergenovv.lab7.dto.*;
import com.nurbergenovv.lab7.entity.AutoSalon;
import com.nurbergenovv.lab7.entity.Car;
import com.nurbergenovv.lab7.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "salon", expression = "java(toSalonShort(car.getSalon()))")
    @Mapping(target = "owners", expression = "java(toOwnerShortList(car.getOwners()))")
    CarResponseDTO toResponseDto(Car car);

    List<CarResponseDTO> toResponseDtoList(List<Car> cars);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "salon", ignore = true)
    @Mapping(target = "owners", ignore = true)
    Car toEntity(CarRequestDTO dto);

    CarShortDTO toShortDto(Car car);

    List<CarShortDTO> toShortDtoList(List<Car> cars);

    AutoSalonShortDTO toSalonShort(AutoSalon salon);

    List<OwnerShortDTO> toOwnerShortList(List<Owner> owners);

    OwnerShortDTO toOwnerShort(Owner owner);
}