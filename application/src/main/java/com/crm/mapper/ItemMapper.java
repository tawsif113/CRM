package com.crm.mapper;

import com.crm.dto.requestDtos.ItemRequestDto;
import com.crm.dto.responseDtos.ItemResponseDto;
import com.crm.model.Item;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemRequestDto dto);

    ItemResponseDto toDto(Item item);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ItemRequestDto dto, @MappingTarget Item item);
}
