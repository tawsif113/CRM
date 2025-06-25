package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.dto.responseDtos.OpportunityStatsResponse;
import com.crm.enumTypes.OpportunityFrom;
import com.crm.enumTypes.OpportunityStage;
import com.crm.exception.NotFoundException;
import com.crm.mapper.OpportunityMapper;
import com.crm.model.*;
import com.crm.repository.OpportunityRepository;
import com.crm.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OpportunityServiceImp implements OpportunityService {
    private final OpportunityRepository opportunityRepository;
    private final LeadService leadService;
    private final CustomerService customerService;
    private final SalesPersonService salesPersonService;
    private final OpportunityMapper opportunityMapper;
    private final CampaignService campaignService;
    private final ItemService itemService;

    @Transactional
    @Override
    public OpportunityResponseDto create(OpportunityRequestDto dto) {
        try {
            if (dto.getOpportunityFrom() == null) {
                throw new BadRequestException("opportunity_from must be provided");
            }
            if (dto.getOpportunityFrom() == OpportunityFrom.LEAD && dto.getLeadId() == null) {
                throw new BadRequestException("Lead must be provided for lead-originated opportunities");
            }
            if (dto.getOpportunityFrom() == OpportunityFrom.CUSTOMER && dto.getLeadId() != null) {
                throw new BadRequestException("Lead must be null for customer-originated opportunities");
            }

            Opportunity opportunity = opportunityMapper.toEntity(dto);
            setValues(opportunity, dto);

            return opportunityMapper.toDto(opportunityRepository.save(opportunity));

        }  catch (Exception ex) {
            throw new IllegalArgumentException("Failed to create Opportunity: " + ex.getMessage());
        }
    }

    @Override
    public OpportunityResponseDto find(Long id) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Opportunity not found with id: " + id));
        return opportunityMapper.toDto(opportunity);
    }

    @Override
    public OpportunityResponseDto update(Long id, OpportunityRequestDto dto) {

        try {

            Opportunity opportunity = findById(id);

            if (dto.getOpportunityFrom() == null) {
                throw new NotFoundException("opportunity_from must be provided");
            }
            if (dto.getOpportunityFrom() == OpportunityFrom.LEAD && dto.getLeadId() == null) {
                throw new NotFoundException("Lead must be provided for lead-originated opportunities");
            }
            if (dto.getOpportunityFrom() == OpportunityFrom.CUSTOMER && dto.getLeadId() != null) {
                throw new NotFoundException("Lead must be null for customer-originated opportunities");
            }

            opportunityMapper.updateEntity(dto,opportunity);
            setValues(opportunity, dto);

            return opportunityMapper.toDto(opportunityRepository.save(opportunity));
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Failed to update Opportunity: " + ex.getMessage());
        }
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        opportunityRepository.delete(findById(id));
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Opportunity deleted successfully");
        return deleteResponseDto;

    }

    @Override
    public Page<OpportunityResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<Opportunity> opportunities = opportunityRepository.findAll(pageable);
        return opportunities.map(opportunityMapper::toDto);

    }


    @Override
    public OpportunityStatsResponse getStats(){
        int totalOpportunities = (int) opportunityRepository.count();
        List<Object[]> stageCounts = opportunityRepository.getSummaryByStage() ;

        Map<OpportunityStage, Long> stageSummary = new HashMap<>();
        for( Object[] stageCount : stageCounts) {
            OpportunityStage stage = (OpportunityStage) stageCount[0];
            Long count = ((Number) stageCount[1]).longValue();
            stageSummary.put(stage, count);
        }

        return new OpportunityStatsResponse(totalOpportunities, stageSummary);

    }

    @Override
    public Opportunity findById(Long id) {
        return opportunityRepository.findById(id).orElseThrow(() -> new NotFoundException("Opportunity not found with id: " + id));
    }

    public void setValues(Opportunity opportunity, OpportunityRequestDto dto) throws BadRequestException {
        if (dto.getLeadId() != null) {
            Lead lead = leadService.findById(dto.getLeadId());
            opportunity.setLead(lead);
        }
        if( dto.getCustomerId() != null){
            Customer customer = customerService.findById(dto.getCustomerId());
            opportunity.setCustomer(customer);
        }
        if (dto.getNextContactBy() != null) {
            SalesPerson nextContactBy = salesPersonService.findById(dto.getNextContactBy());
            opportunity.setNextContactBy(nextContactBy);
        }

        SalesPerson opportunityOwner = salesPersonService.findById(dto.getOpportunityOwner());
        opportunity.setOpportunityOwner(opportunityOwner);

        if (dto.getSalesCampaign() != null) {
            Campaign campaign = campaignService.findById(dto.getSalesCampaign());
            opportunity.setSalesCampaign(campaign);
        }

        List<OpportunityItem> items = dto.getItems().stream()
                .map(itemDto -> {
                    OpportunityItem line = new OpportunityItem();
                    line.setOpportunity(opportunity);
                    Item item = itemService.findById(itemDto.getItemId());
                    line.setItem(item);
                    line.setQuantity(itemDto.getQuantity());
                    return line;
                })
                .collect(Collectors.toList());
        if (items.isEmpty()) {
            throw new BadRequestException("At least one item must be provided for the opportunity");
        }
        opportunity.setItems(items);
    }
}
