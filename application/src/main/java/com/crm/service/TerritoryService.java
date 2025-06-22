package com.crm.service;

import com.crm.dto.requestDtos.TerritoryRequestDto;
import com.crm.dto.responseDtos.TerritoryResponseDto;
import com.crm.model.Territory;

public interface TerritoryService extends BaseService<TerritoryResponseDto, TerritoryRequestDto> {
    Territory findById(Long territoryId);
}
