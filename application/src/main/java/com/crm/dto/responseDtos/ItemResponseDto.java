package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemResponseDto extends BaseDto {
    private String itemCode;
    private String itemName;
    private BigDecimal quantityOnHand;
    private BigDecimal price;
}
