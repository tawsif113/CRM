package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.CustomerGroupRequestDto;
import com.crm.dto.responseDtos.CustomerGroupResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.CustomerGroupMapper;
import com.crm.model.CustomerGroup;
import com.crm.repository.AccountRepository;
import com.crm.repository.CustomerGroupRepository;
import com.crm.repository.PaymentTermsRepository;
import com.crm.repository.PriceListRepository;
import com.crm.service.CustomerGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerGroupServiceImp implements CustomerGroupService {

    private final CustomerGroupMapper customerGroupMapper;
    private final CustomerGroupRepository customerGroupRepository;
    private final PaymentTermsRepository paymentTermsRepository;
    private final AccountRepository accountRepository;
    private final PriceListRepository priceListRepository;



    @Override
    public CustomerGroupResponseDto create(CustomerGroupRequestDto dto) {
        CustomerGroup customerGroup = customerGroupMapper.toEntity(dto);

        customerGroup.setPaymentTerms(paymentTermsRepository.findById(dto.getPaymentTermsId())
                .orElseThrow(() -> new NotFoundException("Payment terms not found")));

        customerGroup.setReceivableAccount(accountRepository.findById(dto.getReceivableAccountId())
                .orElseThrow(() -> new NotFoundException("Receivable account not found")));

        customerGroup.setAdvanceAccount(accountRepository.findById(dto.getAdvanceAccountId())
                .orElseThrow(() -> new NotFoundException("Advance account not found")));

        customerGroup.setPriceList(priceListRepository.findById(dto.getPriceListId())
                .orElseThrow(() -> new NotFoundException("Price list not found")));

        if(dto.getParentGroupId() != null) {
            CustomerGroup parentGroup = customerGroupRepository.findById(dto.getParentGroupId())
                    .orElseThrow(() -> new NotFoundException("Parent group not found"));
            customerGroup.setParentGroup(parentGroup);
        }

        customerGroup = customerGroupRepository.save(customerGroup);
        return customerGroupMapper.toDto(customerGroup);
    }

    @Override
    public CustomerGroupResponseDto find(Long id) {
        return customerGroupRepository.findById(id)
                .map(customerGroupMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Customer group not found with id: " + id));
    }

    @Override
    public CustomerGroupResponseDto update(Long id, CustomerGroupRequestDto dto) {

        CustomerGroup customerGroup = customerGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer group not found with id: " + id));

        customerGroupMapper.updateEntityFromDto(dto, customerGroup);

        customerGroup.setPaymentTerms(paymentTermsRepository.findById(dto.getPaymentTermsId())
                .orElseThrow(() -> new NotFoundException("Payment terms not found")));

        customerGroup.setReceivableAccount(accountRepository.findById(dto.getReceivableAccountId())
                .orElseThrow(() -> new NotFoundException("Receivable account not found")));

        customerGroup.setAdvanceAccount(accountRepository.findById(dto.getAdvanceAccountId())
                .orElseThrow(() -> new NotFoundException("Advance account not found")));

        customerGroup.setPriceList(priceListRepository.findById(dto.getPriceListId())
                .orElseThrow(() -> new NotFoundException("Price list not found")));

        if(dto.getParentGroupId() != null) {
            CustomerGroup parentGroup = customerGroupRepository.findById(dto.getParentGroupId())
                    .orElseThrow(() -> new NotFoundException("Parent group not found"));
            customerGroup.setParentGroup(parentGroup);
        }

        customerGroup = customerGroupRepository.save(customerGroup);
        return customerGroupMapper.toDto(customerGroup);
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        customerGroupRepository.delete(customerGroupRepository
                .findById(id).orElseThrow(() -> new NotFoundException("Customer group not found with id: " + id)));
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Customer group deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<CustomerGroupResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<CustomerGroup> customerGroups = customerGroupRepository.findAll(pageable);
        return customerGroups.map(customerGroupMapper::toDto);
    }
}
