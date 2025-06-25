package com.crm.service;

import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.dto.responseDtos.OpportunityStatsResponse;
import com.crm.model.Opportunity;

public interface OpportunityService extends BaseService<OpportunityResponseDto, OpportunityRequestDto> {

    OpportunityStatsResponse getStats();
    Opportunity findById(Long id);
}
