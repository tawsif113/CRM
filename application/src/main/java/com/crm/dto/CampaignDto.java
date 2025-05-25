package com.crm.dto;

import com.crm.enumTypes.CampaignStatus;
import com.crm.enumTypes.CampaignType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CampaignDto extends BaseDto {
    private String campaignName;
    private String campaignDescription;
    private CampaignType campaignType;
    private LocalDate startDate;
    private LocalDate endDate;
    private CampaignStatus status;
}
