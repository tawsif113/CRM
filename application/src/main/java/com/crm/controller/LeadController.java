package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.LeadDTO;
import com.crm.dto.requestDtos.LeadRequestDto;
import com.crm.dto.responseDtos.LeadResponseDto;
import com.crm.enumTypes.LeadStatus;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.LeadService;
import com.crm.serviceImpl.LeadServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/leads")
public class LeadController {

    private final LeadService leadService;

    // Create Lead
    @PostMapping
    public ResponseEntity<ApiResponse<LeadResponseDto>> createLead(@RequestBody LeadRequestDto leadDTO) {
        LeadResponseDto createdLead = leadService.create(leadDTO);
        return ApiResponseBuilder.success(createdLead, "Lead created successfully");
    }

    // Get All Leads
    @GetMapping
    public ResponseEntity<ApiResponse<Page<LeadResponseDto>>> getAllLeads(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                     @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                     @RequestParam(defaultValue = "id") String sortField) {
        Page<LeadResponseDto> leads = leadService.findAll(pageNumber, pageSize, direction, sortField );
        return ApiResponseBuilder.success(leads, "Leads retrieved successfully");
    }

    // Get Lead by ID
    @GetMapping("/{leadId}")
    public ResponseEntity<ApiResponse<LeadResponseDto>> getLeadById(@PathVariable Long leadId) {
        LeadResponseDto leadDTO = leadService.find(leadId);
        return ApiResponseBuilder.success(leadDTO, "Lead retrieved successfully");
    }

    // Update Lead
    @PatchMapping("/{leadId}")
    public ResponseEntity<ApiResponse<LeadResponseDto>> updateLead(@PathVariable Long leadId, @RequestBody LeadRequestDto leadDTO) {
        LeadResponseDto updatedLead = leadService.update(leadId, leadDTO);
        return ApiResponseBuilder.success(updatedLead, "Lead updated successfully");
    }

    // Delete Lead
    @DeleteMapping("/{leadId}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteLead(@PathVariable Long leadId) {
        return ApiResponseBuilder.success(leadService.delete(leadId), "Lead deleted successfully");
    }

    // Change Lead Status
    @PatchMapping("/{leadId}/status")
    public ResponseEntity<ApiResponse<LeadResponseDto>> changeLeadStatus(@PathVariable Long leadId, @RequestBody LeadStatus newStatus) {
        LeadResponseDto updatedLead = leadService.changeLeadStatus(leadId, newStatus);
        return ApiResponseBuilder.success(updatedLead, "Lead status updated successfully");
    }

    // Assign/Reassign Lead to Salesperson
    @PatchMapping("/{leadId}/assign")
    public ResponseEntity<ApiResponse<LeadResponseDto>> assignLeadToSalesperson(@PathVariable Long leadId, @RequestBody Long salespersonId) {
        LeadResponseDto updatedLead = leadService.assignLeadToSalesperson(leadId, salespersonId);
        return ApiResponseBuilder.success(updatedLead, "Lead assigned to salesperson successfully");
    }
}
