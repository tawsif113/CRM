package com.crm.repository;

import com.crm.enumTypes.AccountType;
import com.crm.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountCode(String accountCode);

    Optional<Account> findByAccountNumber(String accountNumber);

    Page<Account> findAllByAccountType(AccountType type, Pageable pageable);
}
