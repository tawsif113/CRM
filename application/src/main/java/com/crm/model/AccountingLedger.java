package com.crm.model;

import com.crm.enumTypes.Currency;
import com.crm.enumTypes.LinkedDocumentType;
import com.crm.enumTypes.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "accounting_ledger")
public class AccountingLedger extends BaseEntity {

    private String ledgerName;
    private String ledgerCode;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Boolean isActive;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "posting_date", nullable = false)
    private LocalDate postingDate;

    @Column(name = "debit_amount", precision = 15, scale = 2)
    private BigDecimal debitAmount = BigDecimal.ZERO;

    @Column(name = "credit_amount", precision = 15, scale = 2)
    private BigDecimal creditAmount = BigDecimal.ZERO;

    @Column(name = "balance", precision = 15, scale = 2, nullable = false)
    private BigDecimal balance;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "linked_document_type")
    private LinkedDocumentType linkedDocumentType;

    @Column(name = "linked_document_id")
    private String linkedDocumentId;

    @Column(name = "fiscal_period", length = 20)
    private String fiscalPeriod;

    @Column(name = "is_reconciled")
    private boolean isReconciled = false;


}
