package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.AccountInfoDto;
import com.crm.enumTypes.Currency;
import com.crm.enumTypes.LinkedDocumentType;
import com.crm.enumTypes.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AccountingLedgerResponseDto extends BaseDto {
    private String ledgerName;
    private String ledgerCode;
    private String description;
    private AccountInfoDto account; // Assuming account is identified by ID
    private Currency currency; // Assuming currency is a string representation
    private Boolean isActive;
    private LocalDate transactionDate; // Assuming date is in string format
    private LocalDate postingDate; // Assuming date is in string format
    private BigDecimal debitAmount; // Assuming amount is in string format for precision
    private BigDecimal creditAmount; // Assuming amount is in string format for precision
    private BigDecimal balance; // Assuming amount is in string format for precision
    private String referenceNumber;
    private TransactionType transactionType; // Assuming transaction type is a string representation
    private LinkedDocumentType linkedDocumentType; // Assuming linked document type is a string representation
    private String linkedDocumentId;
    private String fiscalPeriod;  // Assuming fiscal period is a string representation

}
