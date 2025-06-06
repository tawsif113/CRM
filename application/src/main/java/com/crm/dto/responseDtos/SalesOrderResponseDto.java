package com.crm.dto.responseDtos;
import com.crm.dto.BaseDto;
import com.crm.dto.info.CustomerInfoDto;
import com.crm.dto.info.PaymentTermInfoDto;
import com.crm.enumTypes.SalesOrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
public class SalesOrderResponseDto extends BaseDto {

    private String orderNumber;
    private CustomerInfoDto customer;
    private LocalDateTime orderDate;
    private SalesOrderStatus status;
    private BigDecimal totalAmount;
    private PaymentTermInfoDto paymentTermsId;
    private LocalDateTime deliveryDate;
}
