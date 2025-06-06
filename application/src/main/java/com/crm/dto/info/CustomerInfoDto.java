package com.crm.dto.info;

import com.crm.enumTypes.CustomerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerInfoDto {
    private Long id;
    private String customerName;
    private CustomerType customerType;
}
