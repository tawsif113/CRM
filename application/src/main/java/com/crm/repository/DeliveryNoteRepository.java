package com.crm.repository;

import com.crm.model.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
}
