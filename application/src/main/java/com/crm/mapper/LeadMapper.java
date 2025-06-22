package com.crm.mapper;

import com.crm.dto.LeadDTO;
import com.crm.dto.requestDtos.LeadRequestDto;
import com.crm.dto.responseDtos.LeadResponseDto;
import com.crm.model.Lead;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LeadMapper {
    LeadResponseDto toDto(Lead lead);

    @Mapping(target = "territory",ignore = true)
    @Mapping(target = "leadOwner",ignore = true)
    Lead toEntity(LeadRequestDto leadDTO);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(LeadRequestDto leadDTO, @MappingTarget Lead lead);
}
