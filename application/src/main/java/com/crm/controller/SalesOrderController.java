package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.SalesOrderRequestDto;
import com.crm.dto.responseDtos.SalesOrderResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.SalesOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales-orders")
@RequiredArgsConstructor
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    @PostMapping
    public ResponseEntity<ApiResponse<SalesOrderResponseDto>> createSalesOrder(@Valid @RequestBody SalesOrderRequestDto salesOrderRequestDto) {
        SalesOrderResponseDto salesOrderResponseDto = salesOrderService.create(salesOrderRequestDto);
        return ApiResponseBuilder.success(salesOrderResponseDto, "Sales Order created successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesOrderResponseDto>> updateSalesOrder(
            @PathVariable Long id,
            @Valid @RequestBody SalesOrderRequestDto salesOrderRequestDto) {
        SalesOrderResponseDto salesOrderResponseDto = salesOrderService.update(id, salesOrderRequestDto);
        return ApiResponseBuilder.success(salesOrderResponseDto, "Sales Order updated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesOrderResponseDto>> findSalesOrder(@PathVariable Long id) {
        SalesOrderResponseDto salesOrderResponseDto = salesOrderService.find(id);
        return ApiResponseBuilder.success(salesOrderResponseDto, "Sales Order found successfully");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteSalesOrder(@PathVariable Long id) {
        return ApiResponseBuilder.success(salesOrderService.delete(id), "Sales Order deleted successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SalesOrderResponseDto>>> findAllSalesOrders(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                       @RequestParam(defaultValue = "10") int pageSize,
                                                                                       @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                                                       @RequestParam(defaultValue = "id") String sortField
    ) {
        return ApiResponseBuilder.success(salesOrderService.findAll(pageNumber, pageSize, direction, sortField), "Sales Orders retrieved successfully");
    }
}
