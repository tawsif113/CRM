package com.crm.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends BaseEntity {

    @Column(name = "item_code", nullable = false, length = 50, unique = true)
    private String itemCode;

    @Column(name = "item_name", nullable = false, length = 255)
    private String itemName;

    @Column(name = "quantity_on_hand", nullable = false, precision = 15, scale = 2)
    private BigDecimal quantityOnHand;

    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;
}
