package com.crm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "salesperson")
public class SalesPerson extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    ///  TODO : ADD MORE FIELDS AS NEEDED
}
