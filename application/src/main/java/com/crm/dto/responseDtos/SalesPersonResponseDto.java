package com.crm.dto.responseDtos;

import com.crm.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesPersonResponseDto extends BaseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
