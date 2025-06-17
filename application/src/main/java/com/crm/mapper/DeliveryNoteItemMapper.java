package com.crm.mapper;

import com.crm.dto.requestDtos.DeliveryNoteItemRequestDto;
import com.crm.dto.responseDtos.DeliveryNoteItemResponseDto;
import com.crm.model.DeliveryNoteItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DeliveryNoteItemMapper {

    DeliveryNoteItemResponseDto toDto(DeliveryNoteItem deliveryNoteItem);

    @Mapping(target = "deliveryNote", ignore = true)
    DeliveryNoteItem toEntity(DeliveryNoteItemRequestDto dto);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DeliveryNoteItemRequestDto dto, @MappingTarget DeliveryNoteItem deliveryNoteItem);
}
