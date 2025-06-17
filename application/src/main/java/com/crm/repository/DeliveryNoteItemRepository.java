package com.crm.repository;

import com.crm.model.DeliveryNoteItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryNoteItemRepository extends JpaRepository<DeliveryNoteItem, Long> {
}
