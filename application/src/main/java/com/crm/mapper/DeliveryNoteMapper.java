package com.crm.mapper;

import com.crm.dto.requestDtos.DeliveryNoteRequestDto;
import com.crm.dto.responseDtos.DeliveryNoteResponseDto;
import com.crm.model.DeliveryNote;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DeliveryNoteMapper {

     DeliveryNoteResponseDto toDto(DeliveryNote deliveryNote);

     @Mapping(target = "salesOrder", ignore = true)
     DeliveryNote toEntity(DeliveryNoteRequestDto requestDto);

     @InheritConfiguration(name = "toEntity")
     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     void updateEntityFromDto(DeliveryNoteRequestDto requestDto, @MappingTarget DeliveryNote deliveryNote);

}
