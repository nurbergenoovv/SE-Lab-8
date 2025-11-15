package com.nurbergenovv.lab7.controller;

import com.nurbergenovv.lab7.dto.AutoSalonRequestDTO;
import com.nurbergenovv.lab7.dto.AutoSalonResponseDTO;
import com.nurbergenovv.lab7.service.AutoSalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salons")
@RequiredArgsConstructor
public class AutoSalonController {

    private final AutoSalonService autoSalonService;

    @GetMapping
    public List<AutoSalonResponseDTO> getAll() {
        return autoSalonService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoSalonResponseDTO> get(@PathVariable Long id) {
        AutoSalonResponseDTO dto = autoSalonService.get(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AutoSalonResponseDTO> create(@RequestBody AutoSalonRequestDTO dto) {
        AutoSalonResponseDTO created = autoSalonService.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoSalonResponseDTO> update(@PathVariable Long id,
                                                       @RequestBody AutoSalonRequestDTO dto) {
        AutoSalonResponseDTO updated = autoSalonService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = autoSalonService.remove(id);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}