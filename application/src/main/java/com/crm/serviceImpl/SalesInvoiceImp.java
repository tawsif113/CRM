package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.SalesInvoiceRequestDto;
import com.crm.dto.responseDtos.SalesInvoiceResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.SalesInvoiceMapper;
import com.crm.model.SalesInvoice;
import com.crm.repository.SalesInvoiceRepository;
import com.crm.service.SalesInvoiceService;
import com.crm.service.SalesOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesInvoiceImp implements SalesInvoiceService {

    private final SalesInvoiceRepository salesInvoiceRepository;
    private final SalesInvoiceMapper salesInvoiceMapper;
    private final SalesOrderService salesOrderService;

    @Override
    public SalesInvoice findById(Long id) {
        return salesInvoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sales Invoice not found with id: " + id));
    }

    @Override
    public SalesInvoiceResponseDto create(SalesInvoiceRequestDto dto) {

        SalesInvoice salesInvoice = salesInvoiceMapper.toEntity(dto);
        salesInvoice.setSalesOrder(salesOrderService.findById(dto.getSalesOrderId()));

        return salesInvoiceMapper.toDto(salesInvoiceRepository.save(salesInvoice));
    }

    @Override
    public SalesInvoiceResponseDto find(Long id) {
        return salesInvoiceMapper.toDto(findById(id));
    }

    @Override
    public SalesInvoiceResponseDto update(Long id, SalesInvoiceRequestDto dto) {
        SalesInvoice existingInvoice = findById(id);
        SalesInvoice updatedInvoice = salesInvoiceMapper.toEntity(dto);
        updatedInvoice.setSalesOrder(existingInvoice.getSalesOrder());

        return salesInvoiceMapper.toDto(salesInvoiceRepository.save(updatedInvoice));
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        SalesInvoice salesInvoice = findById(id);
        salesInvoiceRepository.delete(salesInvoice);
        return new DeleteResponseDto(id, "Sales Invoice deleted successfully with id: " + id);
    }

    @Override
    public Page<SalesInvoiceResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<SalesInvoice> customers = salesInvoiceRepository.findAll(pageable);
        return customers.map(salesInvoiceMapper::toDto);
    }
}
