package com.crm.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment_terms")
public class PaymentTerms extends BaseEntity{

    @Column(name = "terms_name", nullable = false)
    private String termsName;

    @Column(name = "net_days")
    private Integer netDays;

    @Column(name = "description")
    private String description;
}
