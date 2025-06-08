package com.crm.model;

import com.crm.enumTypes.InvoiceStatus;
import com.crm.enumTypes.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sales_invoice")
public class SalesInvoice extends BaseEntity{

    private String invoiceNumber;
    private String customerName;
    private String customerAddress;
    private LocalDateTime invoiceDate;
    private Double totalAmount;
    private Double taxAmount;
    private Double discountAmount;
    private Double netAmount;

    @ManyToOne
    @JoinColumn(name = "sales_order_id", referencedColumnName = "id", nullable = false)
    private SalesOrder salesOrder;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
