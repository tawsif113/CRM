package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.SalesOrderItemRequestDto;
import com.crm.dto.responseDtos.SalesOrderItemResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.SalesOrderItemMapper;
import com.crm.model.SalesOrder;
import com.crm.model.SalesOrderItem;
import com.crm.repository.SalesOrderItemRepository;
import com.crm.service.SalesOrderItemService;
import com.crm.service.SalesOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesOrderItemServiceImp implements SalesOrderItemService {

    private final SalesOrderItemRepository salesOrderItemRepository;
    private final SalesOrderItemMapper salesOrderItemMapper;
    private final SalesOrderService salesOrderService;

    @Override
    public SalesOrderItemResponseDto create(SalesOrderItemRequestDto dto) {

        SalesOrder salesOrder = salesOrderService.findById(dto.getSalesOrderId());
        SalesOrderItem salesOrderItem = salesOrderItemMapper.toEntity(dto);
        salesOrderItem.setSalesOrder(salesOrder);

        salesOrderItem = salesOrderItemRepository.save(salesOrderItem);

        return salesOrderItemMapper.toDto(salesOrderItem);

    }

    @Override
    public SalesOrderItemResponseDto find(Long id) {
        return salesOrderItemMapper.toDto(findById(id));
    }

    @Override
    public SalesOrderItemResponseDto update(Long id, SalesOrderItemRequestDto dto) {

        SalesOrderItem salesOrderItem = findById(id);
        SalesOrder salesOrder = salesOrderService.findById(dto.getSalesOrderId());
        salesOrderItem.setSalesOrder(salesOrder);
        salesOrderItemMapper.updateSalesOrderItemFromDto(dto, salesOrderItem);
        return salesOrderItemMapper.toDto(
                salesOrderItemRepository.save(salesOrderItem)
        );
    }

    @Override
    public DeleteResponseDto delete(Long id) {

        SalesOrderItem salesOrderItem = findById(id);
        salesOrderItemRepository.delete(salesOrderItem);
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Sales Order Item with id: " + id + " deleted successfully.");
        return deleteResponseDto;
    }

    @Override
    public Page<SalesOrderItemResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<SalesOrderItem> salesOrderItems = salesOrderItemRepository.findAll(pageable);
        return salesOrderItems.map(salesOrderItemMapper::toDto);
    }

    @Override
    public SalesOrderItem findById(Long id) {
        return salesOrderItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sales Order Item not found with id: " + id));
    }
}
