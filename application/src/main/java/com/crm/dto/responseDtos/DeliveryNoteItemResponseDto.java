package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.DeliveryNoteInfoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryNoteItemResponseDto extends BaseDto {

    private DeliveryNoteInfoDto deliveryNote;
    private String itemCode;
    private Integer quantity;
}
