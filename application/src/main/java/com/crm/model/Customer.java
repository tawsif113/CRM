package com.crm.model;

import com.crm.enumTypes.CustomerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity{

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", nullable = false)
    private CustomerType customerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_group")
    private CustomerGroup customerGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "territory_id")
    private Territory territory;

    @Column(name = "billing_currency", length = 3)
    private String billingCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    @Column(name = "credit_limit", precision = 19, scale = 2)
    private BigDecimal creditLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_terms_id")
    private PaymentTerms paymentTerms;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_sales_person",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "sales_person_id"))
    private Set<SalesPerson> salesPersons; // ManyToMany>


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accounting_ledger_id")
    private AccountingLedger accountingLedger;

}
