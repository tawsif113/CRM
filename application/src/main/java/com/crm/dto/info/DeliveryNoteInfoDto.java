package com.crm.dto.info;

import com.crm.enumTypes.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryNoteInfoDto {
    private Long id;
    private DeliveryStatus status;
}
