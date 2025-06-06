package com.crm.model;

import com.crm.enumTypes.SalesOrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sales_order")
public class SalesOrder extends BaseEntity{

    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private SalesOrderStatus status;

    private BigDecimal totalAmount;
    private String paymentTerms;
    private LocalDateTime deliveryDate;
}
