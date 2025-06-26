package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.AccountRequestDto;
import com.crm.dto.responseDtos.AccountResponseDto;
import com.crm.enumTypes.AccountType;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponseDto>> createAccount(@Valid @RequestBody AccountRequestDto accountDto) {
        return ApiResponseBuilder.success(accountService.create(accountDto), "Account created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountResponseDto>> getAccount(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(accountService.find(id), "Account retrieved successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountResponseDto>> updateAccount(@PathVariable(value = "id") Long id, @Valid @RequestBody AccountRequestDto accountDto) {
        return ApiResponseBuilder.success(accountService.update(id, accountDto), "Account updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteAccount(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(accountService.delete(id), "Account deleted successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AccountResponseDto>>> getAllAccounts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortField) {
        return ApiResponseBuilder.success(accountService.findAll(pageNumber, pageSize, direction, sortField), "Accounts retrieved successfully");
    }

    @GetMapping("account-number/{accountNumber}")
    public ResponseEntity<ApiResponse<AccountResponseDto>> getAccountByAccountNumber(@PathVariable String accountNumber) {
        return ApiResponseBuilder.success(accountService.findByAccountNumber(accountNumber), "Account retrieved successfully");
    }

    @GetMapping("account-type/{type}")
    public ResponseEntity<ApiResponse<Page<AccountResponseDto>>> getAccountsByAccountType(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortField) {

        return ApiResponseBuilder.success(accountService.findAllByAccountType(AccountType.valueOf(type),pageNumber,pageSize,sortField, String.valueOf(direction)), "Accounts retrieved successfully");

    }

}
