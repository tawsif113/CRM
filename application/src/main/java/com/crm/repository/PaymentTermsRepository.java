package com.crm.repository;

import com.crm.model.PaymentTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTermsRepository extends JpaRepository<PaymentTerms, Long> {
}
