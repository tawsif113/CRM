package com.crm.mapper;

import com.crm.dto.LeadDTO;
import com.crm.model.Lead;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LeadMapper {

    LeadDTO toDto(Lead lead);

    Lead toEntity(LeadDTO leadDTO);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(LeadDTO leadDTO, @MappingTarget Lead lead);
}
