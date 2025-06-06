package com.crm.dto.responseDtos;

import com.crm.enumTypes.OpportunityStage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class OpportunityStatsResponse {
    public long total;
    public Map<OpportunityStage, Long> byStage;
}
