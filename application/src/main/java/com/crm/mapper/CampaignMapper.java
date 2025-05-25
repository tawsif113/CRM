package com.crm.mapper;

import com.crm.dto.CampaignDto;
import com.crm.model.Campaign;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

     CampaignDto toDto(Campaign campaign);

     Campaign toEntity(CampaignDto campaignDto);

     @InheritConfiguration(name = "toEntity")
     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     void updateEntity(CampaignDto campaignDto, @MappingTarget Campaign campaign);

}
