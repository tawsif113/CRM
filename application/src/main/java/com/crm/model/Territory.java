package com.crm.model;

import com.crm.enumTypes.TerritoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "territory")
public class Territory extends BaseEntity{

    @Column(name = "territory_name")
    private String territoryName;

    @Column(name = "territory_manager")
    private String territoryManager; // Assuming salesperson.id is stored as UUID string

    @Column(name = "region")
    private String region;

    @Enumerated(EnumType.STRING)
    @Column(name = "territory_status", nullable = false)
    private TerritoryStatus territoryStatus = TerritoryStatus.ACTIVE;
}
