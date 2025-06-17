package com.crm.dto.requestDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryNoteItemRequestDto {
    private Long deliveryNoteId;
    private String itemCode;
    private Integer quantity;
}
