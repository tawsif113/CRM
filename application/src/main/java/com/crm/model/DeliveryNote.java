package com.crm.model;

import com.crm.enumTypes.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "delivery_note")
public class DeliveryNote extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "delivery_note_id", nullable = false)
    private SalesOrder salesOrder;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
