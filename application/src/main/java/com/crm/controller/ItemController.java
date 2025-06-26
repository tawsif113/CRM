package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.ItemRequestDto;
import com.crm.dto.responseDtos.ItemResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ApiResponse<ItemResponseDto>> createItem(@Valid @RequestBody ItemRequestDto itemDto) {
        return ApiResponseBuilder.success(itemService.create(itemDto), "Item created successfully");
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponseDto>> getItem(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(itemService.find(id), "Item retrieved successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponseDto>> updateItem(@PathVariable(value = "id") Long id, @Valid @RequestBody ItemRequestDto itemDto) {
        return ApiResponseBuilder.success(itemService.update(id, itemDto), "Item updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteItem(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(itemService.delete(id), "Item deleted successfully");
    }


    @GetMapping
    public ResponseEntity<ApiResponse<Page<ItemResponseDto>>> getAllItems(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortField) {
        return ApiResponseBuilder.success(itemService.findAll(pageNumber, pageSize, direction, sortField), "Items retrieved successfully");
    }

}
