package com.crm.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountInfoDto {
    private Long id;
    private String accountName;
    private String accountNumber;
}
