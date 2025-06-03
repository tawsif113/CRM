package com.crm.service;

import com.crm.dto.requestDtos.CustomerGroupRequestDto;
import com.crm.dto.responseDtos.CustomerGroupResponseDto;
import com.crm.model.CustomerGroup;

public interface CustomerGroupService extends BaseService<CustomerGroupResponseDto, CustomerGroupRequestDto> {

    CustomerGroup findById(Long id);

}
