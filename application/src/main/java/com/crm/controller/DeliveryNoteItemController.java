package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.DeliveryNoteItemRequestDto;
import com.crm.dto.responseDtos.DeliveryNoteItemResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.DeliveryNoteItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery-note-item")
@RequiredArgsConstructor
public class DeliveryNoteItemController {

    private final DeliveryNoteItemService deliveryNoteItemService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryNoteItemResponseDto>>getDeliveryNoteItem(@PathVariable("id") Long id) {
        return ApiResponseBuilder.success(deliveryNoteItemService.find(id), "Delivery Note Item fetched successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Iterable<DeliveryNoteItemResponseDto>>> getAllDeliveryNoteItems(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortField
    ) {
        return ApiResponseBuilder.success(deliveryNoteItemService.findAll(pageNumber, pageSize, direction, sortField), "Delivery Note Items fetched successfully");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DeliveryNoteItemResponseDto>> saveDeliveryNoteItem(@Valid @RequestBody DeliveryNoteItemRequestDto deliveryNoteItemRequestDto) {
        return ApiResponseBuilder.success(deliveryNoteItemService.create(deliveryNoteItemRequestDto), "Delivery Note Item saved successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryNoteItemResponseDto>> updateDeliveryNoteItem(@PathVariable("id") Long id, @Valid @RequestBody DeliveryNoteItemRequestDto deliveryNoteItemRequestDto) {
        return ApiResponseBuilder.success(deliveryNoteItemService.update(id, deliveryNoteItemRequestDto), "Delivery Note Item updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteDeliveryNoteItem(@PathVariable("id") Long id) {
        return ApiResponseBuilder.success(deliveryNoteItemService.delete(id), "Delivery Note Item deleted successfully");
    }

}
