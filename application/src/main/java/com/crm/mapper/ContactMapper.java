package com.crm.mapper;

import com.crm.dto.requestDtos.ContactEntityLinkRequestDTO;
import com.crm.dto.requestDtos.ContactRequestDTO;
import com.crm.dto.responseDtos.ContactEntityLinkResponseDTO;
import com.crm.dto.responseDtos.ContactResponseDTO;
import com.crm.model.Contact;
import com.crm.model.ContactEntityLink;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "associations", source = "associations", qualifiedByName = "mapRequestAssociations")
    Contact toEntity(ContactRequestDTO contactRequestDTO);

    ContactResponseDTO toResponseDTO(Contact contact);


    List<ContactResponseDTO> toResponseDTOList(List<Contact> contacts);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "contact", ignore = true)
    ContactEntityLink toEntityLink(ContactEntityLinkRequestDTO linkRequestDTO);

    ContactEntityLinkResponseDTO toLinkResponseDTO(ContactEntityLink contactEntityLink);

    @Named("mapRequestAssociations")
    default List<ContactEntityLink> mapRequestAssociations(List<ContactEntityLinkRequestDTO> associations) {
        if (associations == null) {
            return null;
        }
        return associations.stream()
                .map(this::toEntityLink)
                .toList();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "associations", ignore = true)
    void updateEntityFromDTO(ContactRequestDTO contactRequestDTO, @MappingTarget Contact contact);



}
