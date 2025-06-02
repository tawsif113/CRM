package com.crm.model;

import com.crm.enumTypes.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseEntity{

    @Column(name = "account_name", nullable = false)
    private String accountName;
    // e.g., "Accounts Receivable – Retail", "Advance Payments"

    /**
     * Type of account (e.g., "RECEIVABLE", "ADVANCE", "BANK", etc.).
     * You could also use an enum if you have a fixed set of types.
     */
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    /**
     * A code or number used in your general ledger chart of accounts.
     * For example: "AR-1001", "ADV-2001".
     */
    @Column(name = "account_code", unique = true)
    private String accountCode;

    private String accountNumber;
}
