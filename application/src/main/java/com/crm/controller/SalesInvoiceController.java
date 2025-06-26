package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.SalesInvoiceRequestDto;
import com.crm.dto.responseDtos.SalesInvoiceResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.SalesInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales-invoices")
public class SalesInvoiceController {

    private final SalesInvoiceService salesInvoiceService;


    @PostMapping
    public ResponseEntity<ApiResponse<SalesInvoiceResponseDto>> createSalesInvoice(@Valid @RequestBody SalesInvoiceRequestDto salesInvoiceDto) {
        return ApiResponseBuilder.success(salesInvoiceService.create(salesInvoiceDto), "Sales Invoice created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesInvoiceResponseDto>> getSalesInvoice(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(salesInvoiceService.find(id), "Sales Invoice retrieved successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesInvoiceResponseDto>> updateSalesInvoice(@PathVariable(value = "id") Long id, @Valid @RequestBody SalesInvoiceRequestDto salesInvoiceDto) {
        return ApiResponseBuilder.success(salesInvoiceService.update(id, salesInvoiceDto), "Sales Invoice updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteSalesInvoice(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(salesInvoiceService.delete(id), "Sales Invoice deleted successfully");
    }


    @GetMapping
    public ResponseEntity<ApiResponse<Page<SalesInvoiceResponseDto>>> getAllSalesInvoices(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortField) {
        return ApiResponseBuilder.success(salesInvoiceService.findAll(pageNumber, pageSize, direction, sortField), "Sales Invoices retrieved successfully");
    }
}
