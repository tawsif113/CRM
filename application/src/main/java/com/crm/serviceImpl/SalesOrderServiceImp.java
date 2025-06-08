package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.SalesOrderRequestDto;
import com.crm.dto.responseDtos.SalesOrderItemResponseDto;
import com.crm.dto.responseDtos.SalesOrderResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.SalesOrderMapper;
import com.crm.model.SalesOrder;
import com.crm.repository.PaymentTermsRepository;
import com.crm.repository.SalesOrderRepository;
import com.crm.service.CustomerService;
import com.crm.service.SalesOrderItemService;
import com.crm.service.SalesOrderService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesOrderServiceImp implements SalesOrderService {

    private final SalesOrderMapper salesOrderMapper;
    private final SalesOrderRepository salesOrderRepository;
    private final CustomerService customerService;
    private final PaymentTermsRepository paymentTermsRepository;
    private final SalesOrderItemService salesOrderItemService;

    public SalesOrderServiceImp(SalesOrderMapper salesOrderMapper,
                                SalesOrderRepository salesOrderRepository,
                                CustomerService customerService,
                                PaymentTermsRepository paymentTermsRepository,
                                @Lazy SalesOrderItemService salesOrderItemService) {
        this.salesOrderMapper = salesOrderMapper;
        this.salesOrderRepository = salesOrderRepository;
        this.customerService = customerService;
        this.paymentTermsRepository = paymentTermsRepository;
        this.salesOrderItemService = salesOrderItemService;
    }

    @Override
    public SalesOrderResponseDto create(SalesOrderRequestDto dto) {

        SalesOrder salesOrder = salesOrderMapper.toSalesOrderEntity(dto);
        salesOrder.setCustomer(customerService.findById(dto.getCustomerId()));
        salesOrder.setPaymentTerms(paymentTermsRepository
                .findById(dto.getPaymentTermsId())
                .orElseThrow(() -> new NotFoundException("Payment Terms not found")));
        salesOrder = salesOrderRepository.save(salesOrder);

        SalesOrderResponseDto salesOrderResponseDto = salesOrderMapper.toSalesOrderResponseDto(salesOrder);

        final SalesOrder finalSalesOrder = salesOrder;
        if (dto.getItems() != null) {
            List<SalesOrderItemResponseDto> result = dto.getItems().stream().map(
                itemDto -> {
                    SalesOrderItemResponseDto itemResponseDto = salesOrderItemService.create(itemDto);
                    Long itemId = itemResponseDto.getId();
                    itemResponseDto = salesOrderItemService.setSalesOrderReference(itemId, finalSalesOrder);
                    return itemResponseDto;
                }
            ).toList();

            salesOrderResponseDto.setItems(result);
        }


        return salesOrderResponseDto;
    }

    @Override
    public SalesOrderResponseDto find(Long id) {
        return null;
    }

    @Override
    public SalesOrderResponseDto update(Long id, SalesOrderRequestDto dto) {
        return null;
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        return null;
    }

    @Override
    public Page<SalesOrderResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        return null;
    }
}
