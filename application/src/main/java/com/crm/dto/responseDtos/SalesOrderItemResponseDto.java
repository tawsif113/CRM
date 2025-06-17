package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.SalesOrderInfoDto;
import com.crm.model.SalesOrder;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalesOrderItemResponseDto extends BaseDto {

    private SalesOrderInfoDto salesOrder;
    private String itemCode;
    private Integer quantity;
    private BigDecimal price;
}
