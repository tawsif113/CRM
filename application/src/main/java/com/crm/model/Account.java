package com.crm.model;

import com.crm.enumTypes.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseEntity{

    @Column(name = "account_name", nullable = false)
    private String accountName;
    // e.g., "Accounts Receivable – Retail", "Advance Payments"

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "account_code", unique = true)
    private String accountCode;

    private String accountNumber;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<AccountingLedger> ledgerEntries;
}
