package com.crm.mapper;

import com.crm.dto.TerritoryDto;
import com.crm.model.Territory;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TerritoryMapper {

    TerritoryDto toDto(Territory territory);

    @Mapping(target = "id", ignore = true)
    Territory toEntity(TerritoryDto territoryDto);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(TerritoryDto territoryDto, @MappingTarget Territory territory);
}
