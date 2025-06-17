package com.crm.service;

import com.crm.dto.requestDtos.PaymentTermsRequestDto;
import com.crm.dto.responseDtos.PaymentTermsResponseDto;
import com.crm.model.PaymentTerms;

public interface PaymentTermsService extends BaseService<PaymentTermsResponseDto,PaymentTermsRequestDto>{
    PaymentTerms findById(Long id);
}
