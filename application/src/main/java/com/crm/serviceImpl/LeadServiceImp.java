package com.crm.serviceImpl;
import com.crm.dto.DeleteResponseDto;
import com.crm.dto.LeadDTO;
import com.crm.mapper.LeadMapper;
import com.crm.model.Lead;
import com.crm.repository.LeadRepository;
import com.crm.service.LeadService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class LeadServiceImp implements LeadService {
    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadMapper leadMapper;




    @Override
    public LeadDTO create(LeadDTO leadDTO) {
        Lead lead = leadMapper.toEntity(leadDTO);
        lead = leadRepository.save(lead);
        return leadMapper.toDto(lead);
    }

    // Get All Leads
    @Override
    public Page<LeadDTO> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<Lead> leads = leadRepository.findAll(pageable);
        return leads.map(leadMapper::toDto);
    }

    // Get Lead by ID
    @Override
    public LeadDTO find(Long leadId) {
        Optional<Lead> lead = leadRepository.findById(leadId);
        return lead.map(leadMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Lead With ID " + leadId + " does not exist"));
    }

    // Update Lead
    @Override
    public LeadDTO update(Long leadId, LeadDTO leadDTO) {
        Optional<Lead> existingLead = leadRepository.findById(leadId);
        if (existingLead.isPresent()) {
            Lead lead = existingLead.get();
            leadMapper.updateEntity(leadDTO, lead);
            lead = leadRepository.save(lead);
            return leadMapper.toDto(lead);
        } else {
            throw new RuntimeException("Lead With ID " + leadId + " does not exist");
        }
    }

    // Delete Lead
    @Override
    public DeleteResponseDto delete(Long leadId) {
        Optional<Lead> lead = leadRepository.findById(leadId);
        if (lead.isPresent()) {
            leadRepository.delete(lead.get());  DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
            deleteResponseDto.setId(leadId);
            deleteResponseDto.setMessage("Lead deleted successfully");
            return deleteResponseDto;
        } else {
            throw new RuntimeException("Lead With ID " + leadId + " does not exist");
        }


    }

    // Change Lead Status
    public LeadDTO changeLeadStatus(Long leadId, String newStatus) {
        Optional<Lead> lead = leadRepository.findById(leadId);
        if (lead.isPresent()) {
            Lead updatedLead = lead.get();
            updatedLead.setLeadStatus(newStatus);
            updatedLead = leadRepository.save(updatedLead);
            return leadMapper.toDto(updatedLead);
        } else {
            throw new RuntimeException("Lead With ID " + leadId + " does not exist");
        }
    }

    // Assign/Reassign Lead to Salesperson
    public LeadDTO assignLeadToSalesperson(Long leadId, String salespersonId) {
        Optional<Lead> lead = leadRepository.findById(leadId);
        if (lead.isPresent()) {
            Lead updatedLead = lead.get();
            updatedLead.setLeadOwner(salespersonId);
            updatedLead = leadRepository.save(updatedLead);
            return leadMapper.toDto(updatedLead);
        } else {
            throw new RuntimeException("Lead With ID " + leadId + " does not exist");
        }
    }
}
