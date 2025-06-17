package com.crm.dto.requestDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesOrderItemRequestDto {
    private Long salesOrderId;
    private String itemCode;
    private Integer quantity;
    private Double price;
}
