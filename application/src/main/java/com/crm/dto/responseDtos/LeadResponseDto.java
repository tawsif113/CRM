package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.SalespersonInfoDto;
import com.crm.dto.info.TerritoryInfoDto;
import com.crm.enumTypes.LeadSource;
import com.crm.enumTypes.LeadStatus;
import com.crm.model.ContactInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadResponseDto extends BaseDto {
    private String leadName;
    private LeadSource leadSource;
    private ContactInfo contactInfo;
    private LeadStatus leadStatus;
    private SalespersonInfoDto leadOwner;
    private TerritoryInfoDto territory;
    private Integer leadRating;
}
