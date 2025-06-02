package com.crm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "price_list")
public class PriceList extends BaseEntity {

    @Column(name = "price_list_name", nullable = false)
    private String priceListName;

    @Column(name = "description")
    private String description;

    @Column(name = "currency_code", length = 3, nullable = false)
    private String currencyCode;

    @Column(name = "markup_percentage", nullable = false)
    private Double markupPercentage;
}
