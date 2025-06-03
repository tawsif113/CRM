package com.crm.controller;

import com.crm.dto.requestDtos.CustomerGroupRequestDto;
import com.crm.dto.responseDtos.CustomerGroupResponseDto;
import com.crm.service.CustomerGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer-groups")
@RequiredArgsConstructor
public class CustomerGroupController {

    private final CustomerGroupService customerGroupService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerGroupResponseDto> getCustomerGroup(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(customerGroupService.find(id));
    }

    @PostMapping
    public ResponseEntity<CustomerGroupResponseDto> createCustomerGroup(@Valid @RequestBody CustomerGroupRequestDto customerGroupRequestDto) {
        return ResponseEntity.ok(customerGroupService.create(customerGroupRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerGroupResponseDto> updateCustomerGroup(@PathVariable(value = "id") Long id, @Valid @RequestBody CustomerGroupRequestDto customerGroupRequestDto) {
        return ResponseEntity.ok(customerGroupService.update(id, customerGroupRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerGroup(@PathVariable(value = "id") Long id) {
        customerGroupService.delete(id);
        return ResponseEntity.ok("Customer group deleted successfully");
    }

    @GetMapping
    public ResponseEntity<Page<CustomerGroupResponseDto>> getAllCustomerGroups(@RequestParam(defaultValue = "0") int pageNumber,
                                                                               @RequestParam(defaultValue = "10") int pageSize,
                                                                               @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                                               @RequestParam(defaultValue = "id") String sortField) {
        return ResponseEntity.ok(customerGroupService.findAll(pageNumber, pageSize, direction, sortField));
    }


}
