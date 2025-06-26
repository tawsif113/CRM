package com.crm.dto.requestDtos;

import com.crm.enumTypes.InvoiceStatus;
import com.crm.enumTypes.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SalesInvoiceRequestDto {
    private String invoiceNumber;
    private String customerName;
    private String customerAddress;
    private LocalDateTime invoiceDate;
    private Double totalAmount;
    private Double taxAmount;
    private Double discountAmount;
    private Double netAmount;
    private Long salesOrderId;
    private InvoiceStatus status;
    private PaymentStatus paymentStatus;

}
