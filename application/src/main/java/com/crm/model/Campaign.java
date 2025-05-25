package com.crm.model;

import com.crm.enumTypes.CampaignStatus;
import com.crm.enumTypes.CampaignType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "campaign")
public class Campaign extends BaseEntity {

    @Column(nullable = false)
    private String campaignName;

    @Column(columnDefinition = "TEXT")
    private String campaignDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CampaignType campaignType;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CampaignStatus status;
}
