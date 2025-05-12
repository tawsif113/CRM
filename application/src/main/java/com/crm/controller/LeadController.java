package com.crm.controller;

import com.crm.dto.LeadDTO;
import com.crm.serviceImpl.LeadServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leads")
public class LeadController {

    @Autowired
    private LeadServiceImp leadService;

    // Create Lead
    @PostMapping
    public ResponseEntity<LeadDTO> createLead(@RequestBody LeadDTO leadDTO) {
        LeadDTO createdLead = leadService.create(leadDTO);
        return ResponseEntity.status(201).body(createdLead);
    }

    // Get All Leads
    @GetMapping
    public ResponseEntity<Page<LeadDTO>> getAllLeads(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                     @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                     @RequestParam(defaultValue = "id") String sortField) {
        Page<LeadDTO> leads = leadService.findAll(pageNumber, pageSize, direction, sortField );
        return ResponseEntity.ok(leads);
    }

    // Get Lead by ID
    @GetMapping("/{leadId}")
    public ResponseEntity<LeadDTO> getLeadById(@PathVariable Long leadId) {
        LeadDTO leadDTO = leadService.find(leadId);
        return ResponseEntity.ok(leadDTO);
    }

    // Update Lead
    @PatchMapping("/{leadId}")
    public ResponseEntity<LeadDTO> updateLead(@PathVariable Long leadId, @RequestBody LeadDTO leadDTO) {
        LeadDTO updatedLead = leadService.update(leadId, leadDTO);
        return ResponseEntity.ok(updatedLead);
    }

    // Delete Lead
    @DeleteMapping("/{leadId}")
    public ResponseEntity<String> deleteLead(@PathVariable Long leadId) {
        leadService.delete(leadId);
        return ResponseEntity.ok("Lead deleted successfully");
    }

    // Change Lead Status
    @PatchMapping("/{leadId}/status")
    public ResponseEntity<LeadDTO> changeLeadStatus(@PathVariable Long leadId, @RequestBody String newStatus) {
        LeadDTO updatedLead = leadService.changeLeadStatus(leadId, newStatus);
        return ResponseEntity.ok(updatedLead);
    }

    // Assign/Reassign Lead to Salesperson
    @PatchMapping("/{leadId}/assign")
    public ResponseEntity<LeadDTO> assignLeadToSalesperson(@PathVariable Long leadId, @RequestBody String salespersonId) {
        LeadDTO updatedLead = leadService.assignLeadToSalesperson(leadId, salespersonId);
        return ResponseEntity.ok(updatedLead);
    }
}
