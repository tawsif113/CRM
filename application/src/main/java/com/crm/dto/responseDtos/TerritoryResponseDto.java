package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import com.crm.dto.info.SalespersonInfoDto;
import com.crm.enumTypes.TerritoryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerritoryResponseDto extends BaseDto {
    private String territoryName;
    private SalespersonInfoDto territoryManager;
    private String region;
    private TerritoryStatus territoryStatus;
}
