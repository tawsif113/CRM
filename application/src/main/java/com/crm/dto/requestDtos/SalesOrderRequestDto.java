package com.crm.dto.requestDtos;

import com.crm.enumTypes.SalesOrderStatus;
import com.crm.model.Customer;
import com.crm.model.PaymentTerms;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class SalesOrderRequestDto {

    private String orderNumber;
    private Long customerId;

    private LocalDateTime orderDate;

    private SalesOrderStatus status;

    private BigDecimal totalAmount;

    private Long paymentTermsId;

    private LocalDateTime deliveryDate;

}
