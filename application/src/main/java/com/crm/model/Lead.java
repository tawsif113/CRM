package com.crm.model;

import com.crm.enumTypes.LeadSource;
import com.crm.enumTypes.LeadStatus;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private LeadSource leadSource;

    @Embedded
    private ContactInfo contactInfo; // Contact Object

    @Column(name = "lead_status")
    @Enumerated(EnumType.STRING)
    private LeadStatus leadStatus;

    @ManyToOne
    @JoinColumn(name = "lead_owner_id", referencedColumnName = "id")
    private SalesPerson leadOwner;  // Salesperson ID

    @ManyToOne
    @JoinColumn(name = "territory_id",referencedColumnName = "id")
    private Territory territory;

    @Column(name = "lead_rating")
    private Integer leadRating;  // Rating (0-100)

}
