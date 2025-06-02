package com.crm.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "customer_group")
public class CustomerGroup extends BaseEntity{

    @Column(name = "customer_group_name", nullable = false)
    private String customerGroupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_group_id")
    private CustomerGroup parentGroup;

    @Column(name = "group_node", nullable = false)
    private Boolean groupNode;

    @Column(name = "credit_limit", precision = 19, scale = 2)
    private BigDecimal creditLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_terms_id")
    private PaymentTerms paymentTerms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receivable_account_id")
    private Account receivableAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advance_account_id")
    private Account advanceAccount;
}
