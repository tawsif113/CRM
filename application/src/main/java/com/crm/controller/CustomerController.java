package com.crm.controller;

import com.crm.dto.requestDtos.CustomerRequestDto;
import com.crm.dto.responseDtos.CustomerResponseDto;
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
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(customerService.find(id));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomers(@RequestParam(defaultValue = "0") int pageNumber,
                                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                                     @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                                     @RequestParam(defaultValue = "id") String sortField) {
        return ResponseEntity.ok(customerService.findAll(pageNumber, pageSize, direction, sortField));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok(customerService.create(customerRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok(customerService.update(id, customerRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(value = "id") Long id) {
        customerService.delete(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

}
