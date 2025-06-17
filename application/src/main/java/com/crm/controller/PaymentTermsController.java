package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.PaymentTermsRequestDto;
import com.crm.dto.responseDtos.PaymentTermsResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.PaymentTermsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment-terms")
@RequiredArgsConstructor
public class PaymentTermsController {

    private final PaymentTermsService paymentTermsService;


    @GetMapping
    public ResponseEntity<ApiResponse<Page<PaymentTermsResponseDto>>> getAllPaymentTerms(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                         @RequestParam(defaultValue = "10") int pageSize,
                                                                                         @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                                                         @RequestParam(defaultValue = "id") String sortField) {
        return ApiResponseBuilder.success(paymentTermsService.findAll(pageNumber, pageSize, direction, sortField), "Payment terms fetched successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentTermsResponseDto>> getPaymentTerms(@PathVariable Long id) {
        return ApiResponseBuilder.success(paymentTermsService.find(id), "Payment terms fetched successfully");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentTermsResponseDto>> createPaymentTerms(@Valid @RequestBody PaymentTermsRequestDto paymentTermsRequestDto) {
        return ApiResponseBuilder.success(paymentTermsService.create(paymentTermsRequestDto), "Payment terms created successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentTermsResponseDto>> updatePaymentTerms(@PathVariable Long id, @Valid @RequestBody PaymentTermsRequestDto paymentTermsRequestDto) {
        return ApiResponseBuilder.success(paymentTermsService.update(id, paymentTermsRequestDto), "Payment terms updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deletePaymentTerms(@PathVariable Long id) {
        return ApiResponseBuilder.success(paymentTermsService.delete(id), "Payment terms deleted successfully");
    }
}
