package com.crm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactInfo {

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;
}
