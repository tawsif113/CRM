package com.crm.dto.responseDtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpportunityItemResponseDto {
    private Long itemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal unitPrice;
}
