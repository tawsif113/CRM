package com.crm.service;

import com.crm.dto.requestDtos.SalesOrderRequestDto;
import com.crm.dto.responseDtos.SalesOrderResponseDto;
import com.crm.model.SalesOrder;
import com.crm.model.SalesOrderItem;

public interface SalesOrderService extends BaseService<SalesOrderResponseDto, SalesOrderRequestDto>{
    SalesOrder findById(Long id);
}
