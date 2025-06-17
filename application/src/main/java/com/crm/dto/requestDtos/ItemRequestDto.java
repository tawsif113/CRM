package com.crm.dto.requestDtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemRequestDto {
    private String itemCode;
    private String itemName;
    private BigDecimal quantityOnHand;
    private BigDecimal price;
}
