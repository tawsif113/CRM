package com.crm.dto;

import com.crm.enumTypes.TerritoryStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerritoryDto extends BaseDto{
    @NotBlank
    private String territoryName;
    private String territoryManager;
    private String region;
    private TerritoryStatus territoryStatus;
}
