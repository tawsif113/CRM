package com.crm.repository;

import com.crm.model.AccountingLedger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountLedgerRepository extends JpaRepository<AccountingLedger,Long> {
}
