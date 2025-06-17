package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.SalesOrderRequestDto;
import com.crm.dto.responseDtos.SalesOrderResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.SalesOrderMapper;
import com.crm.model.SalesOrder;
import com.crm.repository.SalesOrderRepository;
import com.crm.service.CustomerService;
import com.crm.service.PaymentTermsService;
import com.crm.service.SalesOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesOrderServiceImp implements SalesOrderService {

    private final SalesOrderMapper salesOrderMapper;
    private final SalesOrderRepository salesOrderRepository;
    private final PaymentTermsService paymentTermsService;
    private final CustomerService customerService;

    @Override
    public SalesOrderResponseDto create(SalesOrderRequestDto dto) {

        SalesOrder salesOrder = salesOrderMapper.toSalesOrderEntity(dto);
        salesOrder.setCustomer(customerService.findById(dto.getCustomerId()));
        salesOrder.setPaymentTerms(paymentTermsService.findById(dto.getPaymentTermsId()));

        salesOrder = salesOrderRepository.save(salesOrder);
        return salesOrderMapper.toSalesOrderResponseDto(salesOrder);
    }

    @Override
    public SalesOrderResponseDto find(Long id) {
        SalesOrder salesOrder = salesOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sales Order not found with id: " + id));
        return salesOrderMapper.toSalesOrderResponseDto(salesOrder);
    }

    @Override
    public SalesOrderResponseDto update(Long id, SalesOrderRequestDto dto) {
        SalesOrder salesOrder = findById(id);
        salesOrderMapper.updateSalesOrderFromDto(dto, salesOrder);
        salesOrder.setCustomer(customerService.findById(dto.getCustomerId()));
        salesOrder.setPaymentTerms(paymentTermsService.findById(dto.getPaymentTermsId()));
        salesOrder = salesOrderRepository.save(salesOrder);
        return salesOrderMapper.toSalesOrderResponseDto(salesOrder);
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        SalesOrder salesOrder = findById(id);
        salesOrderRepository.delete(salesOrder);
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Sales Order deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<SalesOrderResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Page<SalesOrder> salesOrders = salesOrderRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField))
        );
        return salesOrders.map(salesOrderMapper::toSalesOrderResponseDto);
    }

    @Override
    public SalesOrder findById(Long id) {
        return salesOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sales Order not found with id: " + id));
    }
}
