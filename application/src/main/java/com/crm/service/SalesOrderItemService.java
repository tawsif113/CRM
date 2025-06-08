package com.crm.service;

import com.crm.dto.requestDtos.SalesOrderItemRequestDto;
import com.crm.dto.responseDtos.SalesOrderItemResponseDto;
import com.crm.model.SalesOrder;
import com.crm.model.SalesOrderItem;

public interface SalesOrderItemService extends BaseService<SalesOrderItemResponseDto, SalesOrderItemRequestDto>{
    SalesOrderItem findById(Long id);
    SalesOrderItemResponseDto setSalesOrderReference(Long id, SalesOrder salesOrder);
}
