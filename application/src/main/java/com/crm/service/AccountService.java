package com.crm.service;

import com.crm.dto.requestDtos.AccountRequestDto;
import com.crm.dto.responseDtos.AccountResponseDto;
import com.crm.enumTypes.AccountType;
import com.crm.model.Account;
import org.springframework.data.domain.Page;

public interface AccountService extends BaseService<AccountResponseDto, AccountRequestDto>{
    Account findById(Long id);
    AccountResponseDto findByAccountNumber(String accountNumber);
    Page<AccountResponseDto> findAllByAccountType(AccountType type, int pageNumber, int pageSize, String sortField, String direction);
}
