package com.crm.dto.requestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomerGroupRequestDto {

    @NotBlank(message = "customerGroupName is required")
    private String customerGroupName;

    private Long parentGroupId;

    @NotNull(message = "groupNode (true/false) is required")
    private Boolean groupNode;

    private BigDecimal creditLimit;

    private Long priceListId;

    private Long paymentTermsId;

    private Long receivableAccountId;

    private Long advanceAccountId;

}
