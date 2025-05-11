package com.crm.mapper;

import com.crm.dto.LeadDTO;
import com.crm.model.Lead;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LeadMapper {

    LeadDTO toDto(Lead lead);

    @Mapping(target = "id", ignore = true) // Ignore the ID when mapping from DTO to Entity
    Lead toEntity(LeadDTO leadDTO);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(LeadDTO leadDTO, @MappingTarget Lead lead);
}
