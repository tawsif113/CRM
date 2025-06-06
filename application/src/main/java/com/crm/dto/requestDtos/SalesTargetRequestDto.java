package com.crm.dto.requestDtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SalesTargetRequestDto {

    private Long salespersonId;
    private BigDecimal targetAmount;
    private LocalDate startDate;
    private LocalDate endDate;
}
