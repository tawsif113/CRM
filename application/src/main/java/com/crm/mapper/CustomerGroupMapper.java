package com.crm.mapper;

import com.crm.dto.requestDtos.CustomerGroupRequestDto;
import com.crm.dto.responseDtos.CustomerGroupResponseDto;
import com.crm.model.CustomerGroup;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerGroupMapper {

    @Mapping(target = "parentGroup",ignore = true)
    @Mapping(target = "priceList",ignore = true)
    @Mapping(target = "receivableAccount",ignore = true)
    @Mapping(target = "paymentTerms",ignore = true)
    @Mapping(target = "advanceAccount",ignore = true)
    CustomerGroup toEntity(CustomerGroupRequestDto dto);

    CustomerGroupResponseDto toDto(CustomerGroup entity);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CustomerGroupRequestDto dto, @MappingTarget CustomerGroup entity);
}
