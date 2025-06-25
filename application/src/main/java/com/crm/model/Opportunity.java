package com.crm.model;

import com.crm.enumTypes.Currency;
import com.crm.enumTypes.OpportunityFrom;
import com.crm.enumTypes.OpportunityStage;
import com.crm.enumTypes.OpportunityType;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "opportunity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity extends BaseEntity {

    @Column(name = "opportunity_name", nullable = false, length = 255)
    private String opportunityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id")
    private Lead lead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "opportunity_from", nullable = false, length = 10)
    private OpportunityFrom opportunityFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "opportunity_type", nullable = false, length = 20)
    private OpportunityType opportunityType;

    @Enumerated(EnumType.STRING)
    @Column(name = "opportunity_stage", nullable = false, length = 15)
    private OpportunityStage opportunityStage = OpportunityStage.PROSPECTING;

    @Column(name = "estimated_value", precision = 15, scale = 2)
    private BigDecimal estimatedValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false, length = 3)
    private Currency currency;

    @Column(name = "probability_of_closing")
    private Integer probabilityOfClosing;

    @Column(name = "next_contact_date")
    private LocalDate nextContactDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next_contact_by", referencedColumnName = "id")
    private SalesPerson nextContactBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_owner", referencedColumnName = "id", nullable = false)
    private SalesPerson opportunityOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_campaign")
    private Campaign salesCampaign;
    @OneToMany(
            mappedBy = "opportunity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OpportunityItem> items;

    @Column(name = "source", length = 100)
    private String source;

}
