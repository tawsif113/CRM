package com.crm.dto.requestDtos;

import com.crm.enumTypes.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeliveryNoteRequestDto {
    Long salesOrderId;
    LocalDate deliveryDate;
    DeliveryStatus status;
}
