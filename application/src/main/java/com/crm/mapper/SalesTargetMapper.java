package com.crm.mapper;

import com.crm.dto.requestDtos.SalesTargetRequestDto;
import com.crm.dto.responseDtos.SalesTargetResponseDto;
import com.crm.model.SalesTarget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SalesTargetMapper {

    SalesTargetResponseDto toDto(SalesTarget salesTarget);

    @Mapping(target = "salesperson", ignore = true)
    SalesTarget toEntity(SalesTargetRequestDto requestDto);

    void updateEntityFromDto(SalesTargetRequestDto requestDto, @MappingTarget SalesTarget salesTarget);
}
