package com.crm.mapper;

import com.crm.dto.requestDtos.TerritoryRequestDto;
import com.crm.dto.responseDtos.TerritoryResponseDto;
import com.crm.model.Territory;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TerritoryMapper {

    TerritoryResponseDto toDto(Territory territory);

    Territory toEntity(TerritoryRequestDto territoryDto);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(TerritoryRequestDto territoryDto, @MappingTarget Territory territory);
}
