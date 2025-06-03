package com.crm.mapper;

import com.crm.dto.info.SalespersonInfoDto;
import com.crm.dto.requestDtos.CustomerRequestDto;
import com.crm.dto.responseDtos.CustomerResponseDto;
import com.crm.model.Customer;
import com.crm.model.SalesPerson;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "customerGroup",ignore = true)
    @Mapping(target = "territory",ignore = true)
    @Mapping(target = "priceList",ignore = true)
    @Mapping(target = "paymentTerms",ignore = true)
    @Mapping(target = "salesPersons",ignore = true)
    @Mapping(target = "accountingLedger",ignore = true)
    Customer toEntity(CustomerRequestDto dto);

    @Mapping(target = "salesTeam",expression = "java(toSalespersonInfoDtoList(entity.getSalesPersons()))")
    @Mapping(target = "accountingLedgerId", source = "accountingLedger.id")
    CustomerResponseDto toDto(Customer entity);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CustomerRequestDto dto, @MappingTarget Customer entity);


    default List<SalespersonInfoDto> toSalespersonInfoDtoList(Set<SalesPerson> salesPersons) {
        if (salesPersons == null || salesPersons.isEmpty()) {
            return List.of();
        }
        return salesPersons.stream()
                .map(
                        salesPerson -> {
                            SalespersonInfoDto infoDto = new SalespersonInfoDto();
                            infoDto.setId(salesPerson.getId());
                            infoDto.setFirstName(salesPerson.getFirstName());
                            infoDto.setEmail(salesPerson.getEmail());
                            return infoDto;
                        }
                )
                .collect(Collectors.toList());
    }
}
