package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.enumTypes.ContactStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponseDTO extends BaseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private ContactStatus contactStatus;
    private String address;
    private String designation;
    private String department;
    private List<ContactEntityLinkResponseDTO> associations;
    private Boolean isPrimaryContact;

}
