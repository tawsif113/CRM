package com.crm.controller;

import com.crm.dto.requestDtos.SalesOrderRequestDto;
import com.crm.dto.responseDtos.SalesOrderResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.SalesOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
