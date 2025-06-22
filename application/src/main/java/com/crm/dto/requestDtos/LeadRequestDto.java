package com.crm.dto.requestDtos;

import com.crm.enumTypes.LeadSource;
import com.crm.enumTypes.LeadStatus;
import com.crm.model.ContactInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadRequestDto {
    private String leadName;
    private LeadSource leadSource;
    private ContactInfo contactInfo;
    private LeadStatus leadStatus;
    private Long leadOwner;
    private Long territory;
    private Integer leadRating;
}
