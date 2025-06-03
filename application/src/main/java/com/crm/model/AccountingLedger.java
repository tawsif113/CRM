package com.crm.model;

import com.crm.enumTypes.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounting_ledger")
public class AccountingLedger extends BaseEntity {

    private String ledgerName;
    private String ledgerCode;
    private String description;

    private String account;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Boolean isActive;


}
