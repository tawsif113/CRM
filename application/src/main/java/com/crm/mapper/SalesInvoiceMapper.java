package com.crm.mapper;

import com.crm.dto.requestDtos.SalesInvoiceRequestDto;
import com.crm.dto.responseDtos.SalesInvoiceResponseDto;
import com.crm.model.SalesInvoice;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SalesInvoiceMapper {

     SalesInvoice toEntity(SalesInvoiceRequestDto requestDto);
     SalesInvoiceResponseDto toDto(SalesInvoice salesInvoice);

     @InheritConfiguration(name = "toEntity")
     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     void update(SalesInvoiceRequestDto requestDto, @MappingTarget SalesInvoice salesInvoice);
}
