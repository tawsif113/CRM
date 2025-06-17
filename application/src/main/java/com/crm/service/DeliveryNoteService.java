package com.crm.service;

import com.crm.dto.requestDtos.DeliveryNoteRequestDto;
import com.crm.dto.responseDtos.DeliveryNoteResponseDto;
import com.crm.model.DeliveryNote;

public interface DeliveryNoteService extends BaseService<DeliveryNoteResponseDto, DeliveryNoteRequestDto>{
    DeliveryNote findById(Long id);
}
