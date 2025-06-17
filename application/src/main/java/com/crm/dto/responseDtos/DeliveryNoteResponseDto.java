package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.ItemInfoDto;
import com.crm.dto.info.SalesOrderInfoDto;
import com.crm.enumTypes.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeliveryNoteResponseDto extends BaseDto {
    private SalesOrderInfoDto salesOrder;
    private LocalDate deliveryDate;
    private DeliveryStatus status;
}
