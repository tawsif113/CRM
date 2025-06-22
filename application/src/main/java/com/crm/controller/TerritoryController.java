package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.TerritoryRequestDto;
import com.crm.dto.responseDtos.TerritoryResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
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
    public ResponseEntity<ApiResponse<TerritoryResponseDto>> createTerritory(@Valid @RequestBody TerritoryRequestDto territoryDto) {
        return ApiResponseBuilder.success(territoryService.create(territoryDto), "Territory created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TerritoryResponseDto>> getTerritory(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(territoryService.find(id), "Territory fetched successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<TerritoryResponseDto>> updateTerritory(@PathVariable(value = "id") Long id, @Valid @RequestBody TerritoryRequestDto territoryDto) {
        return ApiResponseBuilder.success(territoryService.update(id, territoryDto), "Territory updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteTerritory(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(territoryService.delete(id), "Territory deleted successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TerritoryResponseDto>>> getAllTerritories(@RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                             @RequestParam(defaultValue = "id") String sortField) {
        return ApiResponseBuilder.success(territoryService.findAll(pageNumber, pageSize, direction, sortField), "Territories fetched successfully");
    }

}
