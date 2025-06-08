package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.enumTypes.EntityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactEntityLinkResponseDTO extends BaseDto {

    private EntityType entityType;
    private Long entityId;
    private Boolean isPrimary;

}