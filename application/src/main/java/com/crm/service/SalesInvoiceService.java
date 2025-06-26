package com.crm.service;

import com.crm.dto.requestDtos.SalesInvoiceRequestDto;
import com.crm.dto.responseDtos.SalesInvoiceResponseDto;
import com.crm.model.SalesInvoice;

public interface SalesInvoiceService extends BaseService<SalesInvoiceResponseDto, SalesInvoiceRequestDto>{
    SalesInvoice findById(Long id);
}
