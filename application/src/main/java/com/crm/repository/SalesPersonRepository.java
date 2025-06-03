package com.crm.repository;

import com.crm.model.SalesPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesPersonRepository extends JpaRepository<SalesPerson, Long> {
}
