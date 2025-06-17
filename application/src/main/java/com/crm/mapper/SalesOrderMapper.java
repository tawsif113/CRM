package com.crm.mapper;

import com.crm.dto.requestDtos.SalesOrderRequestDto;
import com.crm.dto.responseDtos.SalesOrderResponseDto;
import com.crm.model.SalesOrder;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SalesOrderMapper {


    @Mapping(target = "customer",ignore = true)
    @Mapping(target = "paymentTerms",ignore = true)
    SalesOrder toSalesOrderEntity(SalesOrderRequestDto dto);

    SalesOrderResponseDto toSalesOrderResponseDto(SalesOrder entity);

    @InheritConfiguration(name = "toSalesOrderEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSalesOrderFromDto(SalesOrderRequestDto dto, @MappingTarget SalesOrder entity);
}
