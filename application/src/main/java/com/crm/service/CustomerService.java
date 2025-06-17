package com.crm.service;

import com.crm.dto.requestDtos.CustomerRequestDto;
import com.crm.dto.responseDtos.CustomerResponseDto;
import com.crm.model.Customer;

public interface CustomerService extends BaseService<CustomerResponseDto,CustomerRequestDto>{
    Customer findById(Long id);
}
