package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.CustomerRequestDto;
import com.crm.dto.responseDtos.CustomerResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.CustomerMapper;
import com.crm.model.Customer;
import com.crm.model.SalesPerson;
import com.crm.repository.*;
import com.crm.service.CustomerGroupService;
import com.crm.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerGroupService customerGroupService;
    private final PriceListRepository priceListRepository;
    private final PaymentTermsRepository paymentTermsRepository;
    private final SalesPersonRepository salesPersonRepository;
    private final AccountLedgerRepository accountLedgerRepository;
    private final TerritoryRepository territoryRepository;
    private final CustomerMapper mapper;

    public CustomerServiceImp(CustomerRepository customerRepository, CustomerGroupService customerGroupService, PriceListRepository priceListRepository, PaymentTermsRepository paymentTermsRepository, SalesPersonRepository salesPersonRepository, AccountLedgerRepository accountLedgerRepository, TerritoryRepository territoryRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.customerGroupService = customerGroupService;
        this.priceListRepository = priceListRepository;
        this.paymentTermsRepository = paymentTermsRepository;
        this.salesPersonRepository = salesPersonRepository;
        this.accountLedgerRepository = accountLedgerRepository;
        this.territoryRepository = territoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CustomerResponseDto create(CustomerRequestDto dto) {

        Customer customer = mapper.toEntity(dto);
        if(dto.getCustomerGroupId() != null) customer
                .setCustomerGroup(customerGroupService
                        .findById(dto.getCustomerGroupId()));

        if(dto.getPriceListId() != null) customer
                .setPriceList(priceListRepository
                        .findById(dto.getPriceListId())
                        .orElseThrow(() -> new NotFoundException("Price List not found")));

        if(dto.getPaymentTermsId() != null) customer
                .setPaymentTerms(paymentTermsRepository
                        .findById(dto.getPaymentTermsId())
                        .orElseThrow(() -> new NotFoundException("Payment Terms not found")));

        if(dto.getSalesPersonIds() != null && !dto.getSalesPersonIds().isEmpty()) {
            Set<SalesPerson> salesPersons = dto.getSalesPersonIds().stream().map(
                    salesPersonId -> salesPersonRepository
                            .findById(salesPersonId)
                            .orElseThrow(() -> new NotFoundException("Sales Person not found")) )
                    .collect(Collectors.toSet());
            customer.setSalesPersons(salesPersons);
        }

        if(dto.getTerritoryId() != null){
            customer.setTerritory(territoryRepository.findById(dto.getTerritoryId()).orElseThrow(()->new NotFoundException("Territory Not Found")));
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

        if(dto.getPaymentTermsId() != null) customer
                .setPaymentTerms(paymentTermsRepository
                        .findById(dto.getPaymentTermsId())
                        .orElseThrow(() -> new NotFoundException("Payment Terms not found")));
        if(dto.getSalesPersonIds() != null && !dto.getSalesPersonIds().isEmpty()) {
            Set<SalesPerson> salesPersons = dto.getSalesPersonIds().stream().map(
                    salesPersonId -> salesPersonRepository
                            .findById(salesPersonId)
                            .orElseThrow(() -> new NotFoundException("Sales Person not found")) )
                    .collect(Collectors.toSet());
            customer.setSalesPersons(salesPersons);
        }

        if(dto.getTerritoryId() != null){
            customer.setTerritory(territoryRepository.findById(dto.getTerritoryId()).orElseThrow(()->new NotFoundException("Territory Not Found")));
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
}
