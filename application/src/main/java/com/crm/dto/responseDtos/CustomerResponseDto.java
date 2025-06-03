package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.*;
import com.crm.enumTypes.CustomerType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class CustomerResponseDto extends BaseDto {

    private String customerName;
    private CustomerType customerType;
    private CustomerGroupInfoDto customerGroup;

    private TerritoryInfoDto territory;            // id + name
    private String billingCurrency;
    private PriceListInfoDto priceList;            // id + name
    private BigDecimal creditLimit;
    private PaymentTermInfoDto paymentTerms;      // id + name

    private List<SalespersonInfoDto> salesTeam;

    private Long accountingLedgerId;    // id + name
}
