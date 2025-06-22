package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.LeadRequestDto;
import com.crm.dto.responseDtos.LeadResponseDto;
import com.crm.enumTypes.LeadStatus;
import com.crm.exception.NotFoundException;
import com.crm.mapper.LeadMapper;
import com.crm.model.Lead;
import com.crm.model.Territory;
import com.crm.repository.LeadRepository;
import com.crm.service.LeadService;
import com.crm.service.SalesPersonService;
import com.crm.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeadServiceImp implements LeadService {

    private final LeadRepository leadRepository;
    private final TerritoryService territoryService;
    private final SalesPersonService salesPersonService;
    private final LeadMapper leadMapper;

    @Override
    public LeadResponseDto create(LeadRequestDto leadDTO) {
        Lead lead = leadMapper.toEntity(leadDTO);
        Territory territory = territoryService.findById(leadDTO.getTerritory());
        lead.setTerritory(territory);
        if (leadDTO.getLeadOwner() != null) {
            lead.setLeadOwner(salesPersonService.findById(leadDTO.getLeadOwner()));
        }
        lead = leadRepository.save(lead);
        return leadMapper.toDto(lead);
    }

    // Get All Leads
    @Override
    public Page<LeadResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<Lead> leads = leadRepository.findAll(pageable);
        return leads.map(leadMapper::toDto);
    }

    // Get Lead by ID
    @Override
    public LeadResponseDto find(Long leadId) {
        Optional<Lead> lead = leadRepository.findById(leadId);
        return lead.map(leadMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Lead With ID " + leadId + " does not exist"));
    }

    // Update Lead
    @Override
    public LeadResponseDto update(Long leadId, LeadRequestDto leadDTO) {
        Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new NotFoundException("Lead With ID " + leadId + " does not exist"));
        leadMapper.updateEntity(leadDTO, lead);
        lead.setTerritory(territoryService.findById(leadDTO.getTerritory()));
        if (leadDTO.getLeadOwner() != null) {
            lead.setLeadOwner(salesPersonService.findById(leadDTO.getLeadOwner()));
        }
        lead = leadRepository.save(lead);
        return leadMapper.toDto(lead);

    }

    // Delete Lead
    @Override
    public DeleteResponseDto delete(Long leadId) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new NotFoundException("Lead With ID " + leadId + " does not exist"));
        leadRepository.delete(lead);
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setMessage("Lead deleted successfully");
        deleteResponseDto.setId(leadId);
        return deleteResponseDto;
    }

    // Change Lead Status
    @Override
    public LeadResponseDto changeLeadStatus(Long leadId, LeadStatus newStatus) {
        Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new NotFoundException("Lead With ID " + leadId + " does not exist"));
        lead.setLeadStatus(newStatus);
        return leadMapper.toDto(leadRepository.save(lead));
    }

    // Assign/Reassign Lead to Salesperson
    @Override
    public LeadResponseDto assignLeadToSalesperson(Long leadId, Long salespersonId) {
        Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new NotFoundException("Lead With ID " + leadId + " does not exist"));
        lead.setLeadOwner(salesPersonService.findById(salespersonId));
        lead = leadRepository.save(lead);
        return leadMapper.toDto(lead);
    }
}
