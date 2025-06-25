package com.crm.dto.requestDtos;

import com.crm.enumTypes.CampaignStatus;
import com.crm.enumTypes.CampaignType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CampaignRequestDto {
    private String campaignName;
    private String campaignDescription;
    private CampaignType campaignType;
    private LocalDate startDate;
    private LocalDate endDate;
    private CampaignStatus status;
    private Long territoryId;
}
