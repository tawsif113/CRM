package com.crm.mapper;

import com.crm.dto.requestDtos.SalesOrderRequestDto;
import com.crm.dto.responseDtos.SalesOrderResponseDto;
import com.crm.model.SalesOrder;
import com.crm.model.SalesOrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesOrderMapper {

    SalesOrder toSalesOrderEntity(SalesOrderRequestDto dto);

    SalesOrderResponseDto toSalesOrderResponseDto(SalesOrder entity);

    SalesOrderItem toSalesOrderItemEntity(SalesOrderRequestDto dto);



}
