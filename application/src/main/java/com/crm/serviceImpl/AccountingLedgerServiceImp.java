package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.AccountingLedgerRequestDto;
import com.crm.dto.responseDtos.AccountingLedgerResponseDto;
import com.crm.service.AccountingLedgerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AccountingLedgerServiceImp implements AccountingLedgerService {
    @Override
    public AccountingLedgerResponseDto create(AccountingLedgerRequestDto dto) {
        return null;
    }

    @Override
    public AccountingLedgerResponseDto find(Long id) {
        return null;
    }

    @Override
    public AccountingLedgerResponseDto update(Long id, AccountingLedgerRequestDto dto) {
        return null;
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        return null;
    }

    @Override
    public Page<AccountingLedgerResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        return null;
    }
}
