package com.crm.repository;

import com.crm.dto.LeadDTO;
import com.crm.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findByLeadStatus(String leadStatus);

    List<Lead> findByLeadOwner(String leadOwner);

    List<Lead> findByTerritory(String territory);

    Optional<Lead> findById(Long leadId);
}
