package com.crm.dto;

import com.crm.model.ContactInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadDTO extends BaseDto {

    private String leadName;
    private String leadSource;
    private ContactInfo contactInfo;
    private String leadStatus;
    private String leadOwner;
    private String territory;
    private Integer leadRating;


}
