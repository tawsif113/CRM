package com.crm.dto.requestDtos;

import com.crm.enumTypes.TerritoryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerritoryRequestDto {
    private String territoryName;
    private String territoryManager;
    private String region;
    private TerritoryStatus territoryStatus;
}
