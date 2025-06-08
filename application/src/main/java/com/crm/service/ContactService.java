package com.crm.service;

import com.crm.dto.requestDtos.ContactRequestDTO;
import com.crm.dto.responseDtos.ContactResponseDTO;
import com.crm.enumTypes.ContactStatus;
import com.crm.enumTypes.EntityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ContactService extends BaseService<ContactResponseDTO,ContactRequestDTO > {
    Page<ContactResponseDTO> getAllContactsWithAdvancedFilters(String search, ContactStatus status, String firstName, String lastName, String email, String phone, String designation, String department, String address, EntityType entityType, Long entityId, Boolean isPrimary, Pageable pageable);
}
