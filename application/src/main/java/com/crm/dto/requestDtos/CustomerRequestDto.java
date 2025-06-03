package com.crm.dto.requestDtos;

import com.crm.enumTypes.Currency;
import com.crm.enumTypes.CustomerType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CustomerRequestDto {
    @NotNull(message = "Customer name cannot be null")
    private String customerName;

    @NotNull(message = "Customer type cannot be null")
    private CustomerType customerType;

    private Long customerGroupId;

    private Long territoryId;

    private Currency billingCurrency;

    private Long priceListId;

    private BigDecimal creditLimit;

    private Long paymentTermsId;

    private List<Long> salesPersonIds;

    private Long accountingLedgerId;
}
