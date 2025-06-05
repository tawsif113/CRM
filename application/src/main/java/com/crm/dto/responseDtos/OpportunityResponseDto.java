package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.enumTypes.Currency;
import com.crm.enumTypes.OpportunityFrom;
import com.crm.enumTypes.OpportunityStage;
import com.crm.enumTypes.OpportunityType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor

public class OpportunityResponseDto extends BaseDto {
    private String opportunityName;
    private Long leadId;
    private Long customerId;
    private OpportunityFrom opportunityFrom;
    private OpportunityType opportunityType;
    private OpportunityStage opportunityStage;
    private BigDecimal estimatedValue;
    private Currency currency;
    private Integer probabilityOfClosing;
    private LocalDate nextContactDate;
    private Long nextContactBy;
    private Long opportunityOwner;
    private Long salesCampaign;
    private List<OpportunityItemResponseDto> items;

}
