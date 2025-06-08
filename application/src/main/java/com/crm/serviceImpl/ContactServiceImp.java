package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.ContactRequestDTO;
import com.crm.dto.responseDtos.ContactResponseDTO;
import com.crm.enumTypes.ContactStatus;
import com.crm.enumTypes.EntityType;
import com.crm.mapper.ContactMapper;
import com.crm.model.Contact;
import com.crm.model.ContactEntityLink;
import com.crm.repository.ContactRepository;
import com.crm.service.ContactService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Slf4j
@RequiredArgsConstructor
@Service
public class ContactServiceImp implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    @Transactional
    public ContactResponseDTO create(ContactRequestDTO contactRequestDTO) {

        log.info("Creating new contact with name: {} {}", contactRequestDTO.getFirstName(), contactRequestDTO.getLastName());

        Contact contact = contactMapper.toEntity(contactRequestDTO);

        if (contact.getAssociations() != null) {
            for (ContactEntityLink association : contact.getAssociations()) {
                association.setContact(contact);
            }

        }
        Contact savedContact = contactRepository.save(contact);
        log.info("Contact created successfully with ID: {}", savedContact.getId());
        return contactMapper.toResponseDTO(savedContact);

    }

    @Override
    public ContactResponseDTO find(Long id) {
        Contact contact= contactRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Contact not found with id: " + id)
        );
        log.info("Retrieving contact with ID: {}", id);
        return contactMapper.toResponseDTO(contact);
    }

    @Override
    public ContactResponseDTO update(Long id, ContactRequestDTO dto) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Contact not found with id: " + id)
        );
        log.info("Updating contact with ID: {}", id);
        contactMapper.updateEntityFromDTO(dto, contact);
        return contactMapper.toResponseDTO( contactRepository.save(contact));
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Contact not found with id: " + id)
        );
        contactRepository.delete(contact);
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setMessage("Contact deleted successfully");
        deleteResponseDto.setId(id);
        log.info("Contact with ID: {} deleted successfully", id);
        return deleteResponseDto;


    }

    @Override
    public Page<ContactResponseDTO> findAll( int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<Contact> contactPage = contactRepository.findAll(pageable);
        log.info("Retrieved {} contacts from the database", contactPage.getTotalElements());
        return contactPage.map(contactMapper::toResponseDTO);
    }

    @Override
    public Page<ContactResponseDTO> getAllContactsWithAdvancedFilters(String search, ContactStatus status, String firstName, String lastName, String email, String phone,
                                                                      String designation, String department, String address, EntityType entityType,
                                                                      Long entityId, Boolean isPrimary, Pageable pageable) {
        Page<Contact> contactList = contactRepository.findAllWithAdvancedFilters( search,
                 status,  firstName,  lastName,  email,  phone,
                 designation,  department,  address,  entityType,
                 entityId,  isPrimary, pageable);
        log.info("Retrieved {} contacts with advanced filters", contactList.getTotalElements());
        return  contactList.map(contactMapper::toResponseDTO);


    }
}
