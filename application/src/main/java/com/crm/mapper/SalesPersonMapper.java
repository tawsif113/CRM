package com.crm.mapper;

import com.crm.dto.requestDtos.SalesPersonRequestDto;
import com.crm.dto.responseDtos.SalesPersonResponseDto;
import com.crm.model.SalesPerson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SalesPersonMapper {

     SalesPerson toEntity(SalesPersonRequestDto dto);
     SalesPersonResponseDto toDto(SalesPerson salesPerson);
     void updateEntity(SalesPersonRequestDto salesPersonRequestDto, @MappingTarget SalesPerson salesPerson);
}
