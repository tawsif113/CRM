package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.TerritoryDto;
import com.crm.service.TerritoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/territories")
@RequiredArgsConstructor
public class TerritoryController {

    private final TerritoryService territoryService;

    @PostMapping
    public ResponseEntity<TerritoryDto> createTerritory(@Valid @RequestBody TerritoryDto territoryDto) {
        return ResponseEntity.ok(territoryService.create(territoryDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerritoryDto> getTerritory(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(territoryService.find(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TerritoryDto> updateTerritory(@PathVariable(value = "id") Long id, @Valid @RequestBody TerritoryDto territoryDto) {
        return ResponseEntity.ok(territoryService.update(id, territoryDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDto> deleteTerritory(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(territoryService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<TerritoryDto>> getAllTerritories(@RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                             @RequestParam(defaultValue = "id") String sortField) {
        return ResponseEntity.ok(territoryService.findAll(pageNumber, pageSize, direction, sortField));
    }

}
