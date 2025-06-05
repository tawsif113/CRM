package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.enumTypes.OpportunityFrom;
import com.crm.exception.NotFoundException;
import com.crm.mapper.OpportunityMapper;
import com.crm.model.*;
import com.crm.repository.*;
import com.crm.service.OpportunityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class OpportunityServiceImp implements OpportunityService {
    private final OpportunityRepository opportunityRepository;
    private final LeadRepository leadRepository;
    private final CustomerRepository customerRepository;
    private final SalesPersonRepository salesPersonRepository;
    private final OpportunityMapper opportunityMapper;
    private final CampaignRepository campaignRepository;
    private final ItemRepository itemRepository;



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

            Lead lead = null;
            if (dto.getLeadId() != null) {
                lead = leadRepository.findById(dto.getLeadId())
                        .orElseThrow(() -> new NotFoundException("Lead not found with id: " + dto.getLeadId()));
            }

            Customer customer = null;
            if( dto.getCustomerId() != null){
                 customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new NotFoundException("Customer not found with id: " + dto.getCustomerId()));
            }


            SalesPerson nextContactBy = null;
            if (dto.getNextContactBy() != null) {
                nextContactBy = salesPersonRepository.findById(dto.getNextContactBy())
                        .orElseThrow(() -> new NotFoundException("SalesPerson not found with id: " + dto.getNextContactBy()));
            }

            SalesPerson opportunityOwner = salesPersonRepository.findById(dto.getOpportunityOwner())
                    .orElseThrow(() -> new NotFoundException("SalesPerson not found with id: " + dto.getOpportunityOwner()));

            Campaign campaign = null;
            if (dto.getSalesCampaign() != null) {
                campaign = campaignRepository.findById(dto.getSalesCampaign())
                        .orElseThrow(() -> new NotFoundException("Campaign not found with id: " + dto.getSalesCampaign()));
            }

            Opportunity opportunity = opportunityMapper.toEntity(dto);
            opportunity.setLead(lead);
            opportunity.setCustomer(customer);
            opportunity.setNextContactBy(nextContactBy);
            opportunity.setOpportunityOwner(opportunityOwner);
            opportunity.setSalesCampaign(campaign);

            List<OpportunityItem> items = dto.getItems().stream()
                    .map(itemDto -> {
                        OpportunityItem line = new OpportunityItem();
                        line.setOpportunity(opportunity);
                        Item item = itemRepository.findById(itemDto.getItemId())
                                .orElseThrow(() -> new NotFoundException("Item not found with id: " + itemDto.getItemId()));
                        line.setItem(item);
                        line.setQuantity(itemDto.getQuantity());
                        return line;
                    })
                    .collect(Collectors.toList());
            if (items.isEmpty()) {
                throw new BadRequestException("At least one item must be provided for the opportunity");
            }
            opportunity.setItems(items);
            Opportunity savedOpp = opportunityRepository.save(opportunity);



            return opportunityMapper.toDto(savedOpp);

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
            Opportunity opportunity = opportunityRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Opportunity not found with id: " + id));

            if (dto.getOpportunityFrom() == null) {
                throw new NotFoundException("opportunity_from must be provided");
            }
            if (dto.getOpportunityFrom() == OpportunityFrom.LEAD && dto.getLeadId() == null) {
                throw new NotFoundException("Lead must be provided for lead-originated opportunities");
            }
            if (dto.getOpportunityFrom() == OpportunityFrom.CUSTOMER && dto.getLeadId() != null) {
                throw new NotFoundException("Lead must be null for customer-originated opportunities");
            }

            if (dto.getLeadId() != null) {
                leadRepository.findById(dto.getLeadId())
                        .orElseThrow(() -> new NotFoundException("Lead not found with id: " + dto.getLeadId()));
            }


            if (dto.getCustomerId() != null) {
              customerRepository.findById(dto.getCustomerId())
                        .orElseThrow(() -> new NotFoundException("Customer not found with id: " + dto.getCustomerId()));
            }
            opportunityMapper.updateEntity(dto,opportunity);
            return opportunityMapper.toDto(opportunityRepository.save(opportunity));
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Failed to update Opportunity: " + ex.getMessage());
        }
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        opportunityRepository.findById(id).orElseThrow(()-> new NotFoundException("Opportunity not found with id: " + id));
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        opportunityRepository.deleteById(id);
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
}
