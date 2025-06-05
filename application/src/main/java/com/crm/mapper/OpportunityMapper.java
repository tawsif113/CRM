package com.crm.mapper;

import com.crm.dto.LeadDTO;
import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.model.Lead;
import com.crm.model.Opportunity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OpportunityMapper {


    @Mapping(target = "lead", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "nextContactBy", ignore = true)
    @Mapping(target = "opportunityOwner", ignore = true)
    @Mapping(target = "salesCampaign", ignore = true)
    @Mapping(target = "items", ignore = true)
    Opportunity toEntity(OpportunityRequestDto dto);

    @Mapping(source = "lead.id", target = "leadId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "nextContactBy.id", target = "nextContactBy")
    @Mapping(source = "opportunityOwner.id", target = "opportunityOwner")
    @Mapping(source = "salesCampaign.id", target = "salesCampaign")
    OpportunityResponseDto toDto(Opportunity opp);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity( OpportunityRequestDto  opportunityRequestDto , @MappingTarget Opportunity  opportunity );


}
