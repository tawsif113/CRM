package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.SalesPersonRequestDto;
import com.crm.dto.requestDtos.SalesTargetRequestDto;
import com.crm.dto.responseDtos.SalesPersonResponseDto;
import com.crm.dto.responseDtos.SalesTargetResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.SalesPersonService;
import com.crm.service.SalesTargetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales-person")
@RequiredArgsConstructor
public class SalesPersonController {
    private final SalesPersonService salesPersonService;
    private final SalesTargetService salesTargetService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesPersonResponseDto>> getSalesPerson(@PathVariable Long id) {
        return ApiResponseBuilder.success(salesPersonService.find(id), "Sales Person fetched successfully");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SalesPersonResponseDto>> createSalesPerson(@Valid @RequestBody SalesPersonRequestDto salesPersonRequestDto) {
        return ApiResponseBuilder.success(salesPersonService.create(salesPersonRequestDto), "Sales Person created successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesPersonResponseDto>> updateSalesPerson(@PathVariable Long id, @Valid @RequestBody SalesPersonRequestDto salesPersonRequestDto) {
        return ApiResponseBuilder.success(salesPersonService.update(id, salesPersonRequestDto), "Sales Person updated successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SalesPersonResponseDto>>> getAllSalesPersons(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                        @RequestParam(defaultValue = "10") int pageSize,
                                                                                        @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                                                        @RequestParam(defaultValue = "id") String sortField) {
        return ApiResponseBuilder.success(salesPersonService.findAll(pageNumber, pageSize, direction, sortField), "Sales Persons fetched successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteSalesPerson(@PathVariable Long id) {
        return ApiResponseBuilder.success(salesPersonService.delete(id), "Sales Person deleted successfully");
    }

    @GetMapping("/{id}/sales-target")
    public ResponseEntity<ApiResponse<SalesTargetResponseDto>> getSalesTarget(@PathVariable Long id) {
        return ApiResponseBuilder.success(salesTargetService.find(id), "Sales Target fetched successfully");
    }

    @PostMapping("/sales-target")
    public ResponseEntity<ApiResponse<SalesTargetResponseDto>> createSalesTarget(@Valid @RequestBody SalesTargetRequestDto salesTargetRequestDto) {
        return ApiResponseBuilder.success(salesTargetService.create(salesTargetRequestDto), "Sales Target created successfully");
    }
    @PatchMapping("/{id}/sales-target")
    public ResponseEntity<ApiResponse<SalesTargetResponseDto>> updateSalesTarget(@PathVariable Long id, @Valid @RequestBody SalesTargetRequestDto salesTargetRequestDto) {
        return ApiResponseBuilder.success(salesTargetService.update(id, salesTargetRequestDto), "Sales Target updated successfully");
    }

    @DeleteMapping("/{id}/sales-target")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteSalesTarget(@PathVariable Long id) {
        return ApiResponseBuilder.success(salesTargetService.delete(id), "Sales Target deleted successfully");
    }

    @GetMapping("/sales-targets")
    public ResponseEntity<ApiResponse<Page<SalesTargetResponseDto>>> getAllSalesTargets(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                        @RequestParam(defaultValue = "10") int pageSize,
                                                                                        @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                                                        @RequestParam(defaultValue = "id") String sortField) {
        return ApiResponseBuilder.success(salesTargetService.findAll(pageNumber, pageSize, direction, sortField), "Sales Targets fetched successfully");
    }
    @GetMapping("/sales-targets/{sales-target-id}")
    public ResponseEntity<ApiResponse<SalesTargetResponseDto>> getSalesTargetById(@PathVariable(value = "sales-target-id") Long id) {
        return ApiResponseBuilder.success(salesTargetService.find(id), "Sales Target fetched successfully");
    }

}
