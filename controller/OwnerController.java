package com.nurbergenovv.lab7.controller;

import com.nurbergenovv.lab7.dto.OwnerRequestDTO;
import com.nurbergenovv.lab7.dto.OwnerResponseDTO;
import com.nurbergenovv.lab7.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    public List<OwnerResponseDTO> getAll() {
        return ownerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> get(@PathVariable Long id) {
        OwnerResponseDTO dto = ownerService.get(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<OwnerResponseDTO> create(@RequestBody OwnerRequestDTO dto) {
        OwnerResponseDTO created = ownerService.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> update(@PathVariable Long id,
                                                   @RequestBody OwnerRequestDTO dto) {
        OwnerResponseDTO updated = ownerService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = ownerService.remove(id);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}