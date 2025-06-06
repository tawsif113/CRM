package com.crm.service;

import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.dto.responseDtos.OpportunityStatsResponse;

public interface OpportunityService extends BaseService<OpportunityResponseDto, OpportunityRequestDto> {

    OpportunityStatsResponse getStats();
}
