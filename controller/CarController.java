package com.nurbergenovv.lab7.controller;

import com.nurbergenovv.lab7.dto.CarRequestDTO;
import com.nurbergenovv.lab7.dto.CarResponseDTO;
import com.nurbergenovv.lab7.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<CarResponseDTO> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> get(@PathVariable Long id) {
        CarResponseDTO dto = carService.get(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CarResponseDTO> create(@RequestBody CarRequestDTO dto) {
        CarResponseDTO created = carService.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> update(@PathVariable Long id,
                                                 @RequestBody CarRequestDTO dto) {
        CarResponseDTO updated = carService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = carService.remove(id);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}