package com.crm.service;

import com.crm.dto.requestDtos.ItemRequestDto;
import com.crm.dto.responseDtos.ItemResponseDto;
import com.crm.model.Item;

public interface ItemService extends BaseService<ItemResponseDto, ItemRequestDto> {

    Item findById(Long id);

}
