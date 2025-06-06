package com.crm.service;

import com.crm.dto.requestDtos.SalesPersonRequestDto;
import com.crm.dto.responseDtos.SalesPersonResponseDto;
import com.crm.model.SalesPerson;

public interface SalesPersonService extends BaseService<SalesPersonResponseDto, SalesPersonRequestDto> {
    SalesPerson findById(Long id);
}
