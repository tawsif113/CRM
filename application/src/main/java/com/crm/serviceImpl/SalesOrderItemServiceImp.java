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

    @Override
    public SalesOrderItemResponseDto create(SalesOrderItemRequestDto dto) {
        return salesOrderItemMapper.toSalesOrderItemResponseDto(
                salesOrderItemRepository.save(
                        salesOrderItemMapper.toSalesOrderItemEntity(dto)
                )
        );
        ///  after creating always set the SalesOrder reference. Method is in the interface
    }

    @Override
    public SalesOrderItemResponseDto find(Long id) {
        return salesOrderItemMapper.toSalesOrderItemResponseDto(
                findById(id));
    }

    @Override
    public SalesOrderItemResponseDto update(Long id, SalesOrderItemRequestDto dto) {

        SalesOrderItem salesOrderItem = findById(id);
        salesOrderItemMapper.updateSalesOrderItemFromDto(dto, salesOrderItem);
        return salesOrderItemMapper.toSalesOrderItemResponseDto(
                salesOrderItemRepository.save(salesOrderItem)
        );
        ///  after updating always set the SalesOrder reference. Method is in the interface
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
        return salesOrderItems.map(salesOrderItemMapper::toSalesOrderItemResponseDto);
    }

    @Override
    public SalesOrderItem findById(Long id) {
        return salesOrderItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sales Order Item not found with id: " + id));
    }

    @Override
    public SalesOrderItemResponseDto setSalesOrderReference(Long id, SalesOrder salesOrder) {
        SalesOrderItem salesOrderItem = findById(id);
        salesOrderItem.setSalesOrder(salesOrder);
        return salesOrderItemMapper.toSalesOrderItemResponseDto(
                salesOrderItemRepository.save(salesOrderItem)
        );
    }
}
