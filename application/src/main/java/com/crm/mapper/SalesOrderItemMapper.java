package com.crm.mapper;

import com.crm.dto.requestDtos.SalesOrderItemRequestDto;
import com.crm.dto.responseDtos.SalesOrderItemResponseDto;
import com.crm.model.SalesOrderItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SalesOrderItemMapper {

    @Mapping(target = "salesOrder", ignore = true)
    SalesOrderItem toEntity(SalesOrderItemRequestDto dto);

    SalesOrderItemResponseDto toDto(SalesOrderItem entity);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateSalesOrderItemFromDto(SalesOrderItemRequestDto dto, @MappingTarget SalesOrderItem entity);

}
