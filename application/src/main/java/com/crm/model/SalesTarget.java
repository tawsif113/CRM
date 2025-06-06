package com.crm.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "sales_target")
public class SalesTarget extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "salesperson_id")
    private SalesPerson salesperson;

    @Column(name = "target_amount", precision = 19, scale = 2)
    private BigDecimal targetAmount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
}
