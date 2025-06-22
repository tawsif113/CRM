package com.crm.repository;

import com.crm.enumTypes.LeadStatus;
import com.crm.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findByLeadStatus(LeadStatus leadStatus);
    List<Lead> findByTerritoryId(Long territoryId);
    List<Lead> findByLeadOwnerId(Long salespersonId);
}
