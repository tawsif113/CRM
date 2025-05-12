package com.crm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="lead")
public class Lead  extends BaseEntity{

    @Column(name = "lead_name")
    private String leadName;

    @Column(name = "lead_source")
    private String leadSource;

    @Embedded
    private ContactInfo contactInfo; // Contact Object

    @Column(name = "lead_status")
    private String leadStatus;  // Status Enum (New, Contacted, Qualified, Lost, Converted)

    @Column(name = "lead_owner")
    private String leadOwner;  // Salesperson ID

    @Column(name = "territory")
    private String territory;

    @Column(name = "lead_rating")
    private Integer leadRating;  // Rating (0-100)

}
