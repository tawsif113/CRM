package com.crm.dto.requestDtos;

import com.crm.enumTypes.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDto {
    private String accountName; // e.g., "Accounts Receivable – Retail", "Advance Payments"
    private AccountType accountType; // e.g., "ASSET", "LIABILITY", "EQUITY", "REVENUE", "EXPENSE"
    private String accountCode; // Unique code for the account
    private String accountNumber; // Account number, if applicable
}
