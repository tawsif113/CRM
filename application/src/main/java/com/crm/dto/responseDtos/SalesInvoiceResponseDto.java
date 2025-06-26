package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.SalesOrderInfoDto;
import com.crm.enumTypes.InvoiceStatus;
import com.crm.enumTypes.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SalesInvoiceResponseDto extends BaseDto {

    private String invoiceNumber;
    private String customerName;
    private String customerAddress;
    private LocalDateTime invoiceDate;
    private Double totalAmount;
    private Double taxAmount;
    private Double discountAmount;
    private Double netAmount;
    private SalesOrderInfoDto salesOrder;
    private InvoiceStatus status;
    private PaymentStatus paymentStatus;
}
