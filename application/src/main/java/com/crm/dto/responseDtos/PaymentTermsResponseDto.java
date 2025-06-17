package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentTermsResponseDto extends BaseDto {
    private String termsName;
    private Integer netDays;
    private String description;
}
