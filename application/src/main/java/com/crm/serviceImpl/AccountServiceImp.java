package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.AccountRequestDto;
import com.crm.dto.responseDtos.AccountResponseDto;
import com.crm.enumTypes.AccountType;
import com.crm.exception.NotFoundException;
import com.crm.mapper.AccountMapper;
import com.crm.model.Account;
import com.crm.repository.AccountRepository;
import com.crm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found with id: " + id));
    }

    @Override
    public AccountResponseDto findByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new NotFoundException("Account not found with account number: " + accountNumber));
        return accountMapper.toDto(account);
    }

    @Override
    public Page<AccountResponseDto> findAllByAccountType(
            AccountType type,
            int pageNumber,
            int pageSize,
            String sortField,
            String direction
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<Account> accounts = accountRepository.findAllByAccountType(type, pageRequest);
        if (accounts.isEmpty()) {
            throw new NotFoundException("No accounts found with type: " + type);
        }
        return accounts.map(accountMapper::toDto);
    }


    @Override
    public AccountResponseDto create(AccountRequestDto dto) {
        Account account = accountMapper.toEntity(dto);
        return accountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public AccountResponseDto find(Long id) {
        return accountMapper.toDto(findById(id));
    }

    @Override
    public AccountResponseDto update(Long id, AccountRequestDto dto) {
        Account account = findById(id);
        accountMapper.update(dto, account);
        return accountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        Account account = findById(id);
        accountRepository.delete(account);
        return new DeleteResponseDto(id, "Account with id " + id + " deleted successfully.");
    }

    @Override
    public Page<AccountResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Page<Account> accounts = accountRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField)));
        return accounts.map(accountMapper::toDto);
    }
}
