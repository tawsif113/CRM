package com.crm.controller;


import com.crm.dto.requestDtos.ContactRequestDTO;
import com.crm.dto.responseDtos.ContactResponseDTO;
import com.crm.enumTypes.ContactStatus;
import com.crm.enumTypes.EntityType;
import com.crm.model.Contact;
import com.crm.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<Page<ContactResponseDTO>> getAllContacts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) ContactStatus status,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String address,

            @RequestParam(required = false) EntityType entityType,
            @RequestParam(required = false) Long entityId,
            @RequestParam(required = false) Boolean isPrimary,


            // Pagination and sorting
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection) {



        Pageable pageable = PageRequest.of(page, size, sortDirection, sortBy);
        Page<ContactResponseDTO> contacts = contactService.getAllContactsWithAdvancedFilters(
                search, status, firstName, lastName, email, phone, designation, department, address,
                entityType, entityId, isPrimary, pageable);

        return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(@RequestBody ContactRequestDTO contactDto) {
        ContactResponseDTO createdContact = contactService.create(contactDto);
        return ResponseEntity.status(201).body(createdContact);
    }
}
