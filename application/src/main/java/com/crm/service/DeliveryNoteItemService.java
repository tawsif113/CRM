package com.crm.service;

import com.crm.dto.requestDtos.DeliveryNoteItemRequestDto;
import com.crm.dto.responseDtos.DeliveryNoteItemResponseDto;
import com.crm.model.DeliveryNoteItem;

public interface DeliveryNoteItemService extends BaseService<DeliveryNoteItemResponseDto, DeliveryNoteItemRequestDto>{
    DeliveryNoteItem findById(Long id);
}
