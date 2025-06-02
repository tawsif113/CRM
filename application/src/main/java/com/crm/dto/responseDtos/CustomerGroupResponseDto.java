package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.AccountInfoDto;
import com.crm.dto.info.PaymentTermInfoDto;
import com.crm.dto.info.PriceListInfoDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerGroupResponseDto extends BaseDto {

    private String customerGroupName;

    private String parentGroupId;

    private Boolean groupNode;

    private String creditLimit;

    private PriceListInfoDto priceList;

    private PaymentTermInfoDto paymentTerms;

    private AccountInfoDto receivableAccount;

    private AccountInfoDto advanceAccount;
}
