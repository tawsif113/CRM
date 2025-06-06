package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.SalespersonInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SalesTargetResponseDto extends BaseDto {
    private SalespersonInfoDto salesperson;
    private BigDecimal targetAmount;
    private LocalDate startDate;
    private LocalDate endDate;
}
