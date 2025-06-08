package com.crm.mapper;

import com.crm.dto.requestDtos.SalesOrderItemRequestDto;
import com.crm.dto.responseDtos.SalesOrderItemResponseDto;
import com.crm.model.SalesOrderItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SalesOrderItemMapper {

    @Mapping(target = "salesOrder", ignore = true)
    SalesOrderItem toSalesOrderItemEntity(SalesOrderItemRequestDto dto);

    SalesOrderItemResponseDto toSalesOrderItemResponseDto(SalesOrderItem entity);

    @InheritConfiguration(name = "toSalesOrderItemEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateSalesOrderItemFromDto(SalesOrderItemRequestDto dto, @MappingTarget SalesOrderItem entity);

}
