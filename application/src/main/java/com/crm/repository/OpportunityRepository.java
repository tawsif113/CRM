package com.crm.repository;

import com.crm.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    @Query("SELECT o.opportunityStage, COUNT(o) FROM Opportunity o GROUP BY o.opportunityStage")
    List<Object[]> getSummaryByStage();

}
