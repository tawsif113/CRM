package com.crm.mapper;

import com.crm.dto.requestDtos.AccountRequestDto;
import com.crm.dto.responseDtos.AccountResponseDto;
import com.crm.model.Account;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

     AccountResponseDto toDto(Account account);
     Account toEntity(AccountRequestDto dto);

     @InheritConfiguration(name = "toEntity")
     @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
     void update(AccountRequestDto dto, @MappingTarget Account account);
}
