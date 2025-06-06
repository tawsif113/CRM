package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.CustomerRequestDto;
import com.crm.dto.responseDtos.CustomerResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getCustomer(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(customerService.find(id),"Customer fetched successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CustomerResponseDto>>> getAllCustomers(@RequestParam(defaultValue = "0") int pageNumber,
                                                       @RequestParam(defaultValue = "10") int pageSize,
                                                       @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                       @RequestParam(defaultValue = "id") String sortField) {
        return ApiResponseBuilder.success(customerService.findAll(pageNumber, pageSize, direction, sortField),"Customers fetched successfully");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponseDto>> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ApiResponseBuilder.success(customerService.create(customerRequestDto), "Customer created successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> updateCustomer(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ApiResponseBuilder.success(customerService.update(id, customerRequestDto), "Customer updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteCustomer(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(customerService.delete(id),"Customer deleted successfully");
    }

}
