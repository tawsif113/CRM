package com.crm.dto.requestDtos;

import com.crm.enumTypes.OpportunityFrom;
import com.crm.enumTypes.OpportunityStage;
import com.crm.enumTypes.OpportunityType;
import com.crm.enumTypes.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OpportunityRequestDto {
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
    private List<OpportunityItemRequestDto> items;  // <-- list of itemId+quantity
    private String source;
}
