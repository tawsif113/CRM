package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.CustomerRequestDto;
import com.crm.dto.responseDtos.CustomerResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.CustomerMapper;
import com.crm.model.Customer;
import com.crm.model.SalesOrder;
import com.crm.model.SalesPerson;
import com.crm.repository.*;
import com.crm.service.CustomerGroupService;
import com.crm.service.CustomerService;
import com.crm.service.PaymentTermsService;
import com.crm.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerGroupService customerGroupService;
    private final PriceListRepository priceListRepository;
    private final PaymentTermsService paymentTermsService;
    private final SalesPersonRepository salesPersonRepository;
    private final AccountLedgerRepository accountLedgerRepository;
    private final TerritoryService territoryService;
    private final CustomerMapper mapper;

    @Override
    public CustomerResponseDto create(CustomerRequestDto dto) {

        Customer customer = mapper.toEntity(dto);
        if(dto.getCustomerGroupId() != null)
            customer.setCustomerGroup(customerGroupService.findById(dto.getCustomerGroupId()));

        if(dto.getPriceListId() != null) customer
                .setPriceList(priceListRepository
                        .findById(dto.getPriceListId())
                        .orElseThrow(() -> new NotFoundException("Price List not found")));

        if(dto.getPaymentTermsId() != null) customer.setPaymentTerms(paymentTermsService.findById(dto.getPaymentTermsId()));

        if(dto.getSalesPersonIds() != null && !dto.getSalesPersonIds().isEmpty()) {
            Set<SalesPerson> salesPersons = dto.getSalesPersonIds().stream().map(
                    salesPersonId -> salesPersonRepository
                            .findById(salesPersonId)
                            .orElseThrow(() -> new NotFoundException("Sales Person not found")) )
                    .collect(Collectors.toSet());
            customer.setSalesPersons(salesPersons);
        }

        if(dto.getTerritoryId() != null){
            customer.setTerritory(territoryService.findById(dto.getTerritoryId()));
        }

        if (dto.getAccountingLedgerId() != null) {
            customer.setAccountingLedger(accountLedgerRepository
                    .findById(dto.getAccountingLedgerId())
                    .orElseThrow(() -> new NotFoundException("Accounting Ledger not found")));
        }

        return mapper.toDto(customerRepository.save(customer));

    }

    @Override
    public CustomerResponseDto find(Long id) {
        return mapper.toDto(customerRepository
                .findById(id).orElseThrow(() -> new NotFoundException("Customer not found")));
    }

    @Override
    public CustomerResponseDto update(Long id, CustomerRequestDto dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        mapper.updateEntityFromDto(dto, customer);

        if(dto.getCustomerGroupId() != null) customer
                .setCustomerGroup(customerGroupService
                        .findById(dto.getCustomerGroupId()));

        if(dto.getPriceListId() != null) customer
                .setPriceList(priceListRepository
                        .findById(dto.getPriceListId())
                        .orElseThrow(() -> new NotFoundException("Price List not found")));

        if(dto.getPaymentTermsId() != null) customer.setPaymentTerms(paymentTermsService.findById(dto.getPaymentTermsId()));

        if(dto.getSalesPersonIds() != null && !dto.getSalesPersonIds().isEmpty()) {
            Set<SalesPerson> salesPersons = dto.getSalesPersonIds().stream().map(
                    salesPersonId -> salesPersonRepository
                            .findById(salesPersonId)
                            .orElseThrow(() -> new NotFoundException("Sales Person not found")) )
                    .collect(Collectors.toSet());
            customer.setSalesPersons(salesPersons);
        }

        if(dto.getTerritoryId() != null){
            customer.setTerritory(territoryService.findById(dto.getTerritoryId()));
        }

        if (dto.getAccountingLedgerId() != null) {
            customer.setAccountingLedger(accountLedgerRepository
                    .findById(dto.getAccountingLedgerId())
                    .orElseThrow(() -> new NotFoundException("Accounting Ledger not found")));
        }

        return mapper.toDto(customerRepository.save(customer));

    }

    @Override
    public DeleteResponseDto delete(Long id) {
        customerRepository.delete(customerRepository
                .findById(id).orElseThrow(() -> new NotFoundException("Customer not found")));
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Customer deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<CustomerResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(mapper::toDto);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(()->new NotFoundException("Customer not found"));
    }
}
