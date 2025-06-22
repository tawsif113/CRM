package com.crm.mapper;

import com.crm.dto.requestDtos.CampaignRequestDto;
import com.crm.dto.responseDtos.CampaignResponseDto;
import com.crm.model.Campaign;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

     CampaignResponseDto toDto(Campaign campaign);

     Campaign toEntity(CampaignRequestDto campaignDto);

     @InheritConfiguration(name = "toEntity")
     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     void updateEntity(CampaignRequestDto campaignDto, @MappingTarget Campaign campaign);

}
