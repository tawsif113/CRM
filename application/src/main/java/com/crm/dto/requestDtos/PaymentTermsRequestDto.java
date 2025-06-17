package com.crm.dto.requestDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentTermsRequestDto {

    private String termsName;
    private Integer netDays;
    private String description;
}
