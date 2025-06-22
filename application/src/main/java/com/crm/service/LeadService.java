package com.crm.service;

import com.crm.dto.requestDtos.LeadRequestDto;
import com.crm.dto.responseDtos.LeadResponseDto;
import com.crm.enumTypes.LeadStatus;

public interface LeadService extends BaseService<LeadResponseDto, LeadRequestDto>{
    LeadResponseDto changeLeadStatus(Long leadId, LeadStatus newStatus);
    LeadResponseDto assignLeadToSalesperson(Long leadId, Long salespersonId);
}
