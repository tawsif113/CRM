package com.crm.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "opportunity_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpportunityItem extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_id", nullable = false)
    private Opportunity opportunity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;


}
