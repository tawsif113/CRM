package com.crm.mapper;

import com.crm.dto.requestDtos.PaymentTermsRequestDto;
import com.crm.dto.responseDtos.PaymentTermsResponseDto;
import com.crm.model.PaymentTerms;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaymentTermsMapper {

    PaymentTermsResponseDto toDto(PaymentTerms paymentTerms);

    PaymentTerms toEntity(PaymentTermsRequestDto paymentTermsRequestDto);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PaymentTermsRequestDto paymentTermsRequestDto, @MappingTarget PaymentTerms paymentTerms);
}
