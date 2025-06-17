package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.DeliveryNoteRequestDto;
import com.crm.dto.responseDtos.DeliveryNoteResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.DeliveryNoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery-notes")
@RequiredArgsConstructor
public class DeliveryNoteController {
    private final DeliveryNoteService deliveryNoteService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryNoteResponseDto>> getDeliveryNote(@PathVariable("id") Long id) {
        return ApiResponseBuilder.success(deliveryNoteService.find(id), "Delivery Note fetched successfully");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DeliveryNoteResponseDto>> createDeliveryNote(@Valid @RequestBody DeliveryNoteRequestDto deliveryNoteRequestDto) {
        return ApiResponseBuilder.success(deliveryNoteService.create(deliveryNoteRequestDto), "Delivery Note created successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryNoteResponseDto>> updateDeliveryNote(@PathVariable("id") Long id, @Valid @RequestBody DeliveryNoteRequestDto deliveryNoteRequestDto) {
        return ApiResponseBuilder.success(deliveryNoteService.update(id, deliveryNoteRequestDto), "Delivery Note updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteDeliveryNote(@PathVariable("id") Long id) {
        return ApiResponseBuilder.success(deliveryNoteService.delete(id), "Delivery Note deleted successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DeliveryNoteResponseDto>>> getAllDeliveryNotes(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortField
    ) {
        return ApiResponseBuilder.success(deliveryNoteService.findAll(pageNumber, pageSize, direction, sortField), "Delivery Notes fetched successfully");
    }
}
