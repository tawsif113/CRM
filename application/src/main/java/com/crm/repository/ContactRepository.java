package com.crm.repository;

import com.crm.enumTypes.ContactStatus;
import com.crm.enumTypes.EntityType;
import com.crm.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT DISTINCT c FROM Contact c LEFT JOIN c.associations a WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(CONCAT(COALESCE(c.firstName, ''), ' ', COALESCE(c.lastName, ''))) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(COALESCE(c.email, '')) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(COALESCE(c.phone, '')) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(COALESCE(c.designation, '')) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(COALESCE(c.department, '')) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:status IS NULL OR c.status = :status) AND " +
            "(:firstName IS NULL OR :firstName = '' OR LOWER(COALESCE(c.firstName, '')) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
            "(:lastName IS NULL OR :lastName = '' OR LOWER(COALESCE(c.lastName, '')) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
            "(:email IS NULL OR :email = '' OR LOWER(COALESCE(c.email, '')) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:phone IS NULL OR :phone = '' OR COALESCE(c.phone, '') LIKE CONCAT('%', :phone, '%')) AND " +
            "(:designation IS NULL OR :designation = '' OR LOWER(COALESCE(c.designation, '')) LIKE LOWER(CONCAT('%', :designation, '%'))) AND " +
            "(:department IS NULL OR :department = '' OR LOWER(COALESCE(c.department, '')) LIKE LOWER(CONCAT('%', :department, '%'))) AND " +
            "(:address IS NULL OR :address = '' OR LOWER(COALESCE(c.address, '')) LIKE LOWER(CONCAT('%', :address, '%'))) AND " +
            "(:entityType IS NULL OR a.entityType = :entityType) AND " +
            "(:entityId IS NULL OR a.entityId = :entityId) AND " +
            "(:isPrimary IS NULL OR a.isPrimary = :isPrimary) ")
    Page<Contact> findAllWithAdvancedFilters(
            @Param("search") String search,
            @Param("status") ContactStatus status,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("designation") String designation,
            @Param("department") String department,
            @Param("address") String address,
            @Param("entityType") EntityType entityType,
            @Param("entityId") Long entityId,
            @Param("isPrimary") Boolean isPrimary,
            Pageable pageable
    );
}
