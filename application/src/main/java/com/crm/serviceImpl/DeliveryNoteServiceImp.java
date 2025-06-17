package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.DeliveryNoteRequestDto;
import com.crm.dto.responseDtos.DeliveryNoteResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.DeliveryNoteMapper;
import com.crm.model.DeliveryNote;
import com.crm.repository.DeliveryNoteRepository;
import com.crm.service.DeliveryNoteService;
import com.crm.service.SalesOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryNoteServiceImp implements DeliveryNoteService {

    private final DeliveryNoteRepository deliveryNoteRepository;
    private final DeliveryNoteMapper deliveryNoteMapper;
    private final SalesOrderService salesOrderService;

    @Override
    public DeliveryNote findById(Long id) {
        return deliveryNoteRepository.findById(id).orElseThrow(() -> new NotFoundException("Delivery note not found"));
    }

    @Override
    public DeliveryNoteResponseDto create(DeliveryNoteRequestDto dto) {
        DeliveryNote deliveryNote = deliveryNoteMapper.toEntity(dto);
        deliveryNote.setSalesOrder(salesOrderService.findById(dto.getSalesOrderId()));
        DeliveryNote savedDeliveryNote = deliveryNoteRepository.save(deliveryNote);
        return deliveryNoteMapper.toDto(savedDeliveryNote);
    }

    @Override
    public DeliveryNoteResponseDto find(Long id) {
        return deliveryNoteMapper.toDto(findById(id));
    }

    @Override
    public DeliveryNoteResponseDto update(Long id, DeliveryNoteRequestDto dto) {

        DeliveryNote existingDeliveryNote = findById(id);
        deliveryNoteMapper.updateEntityFromDto(dto, existingDeliveryNote);
        existingDeliveryNote.setSalesOrder(salesOrderService.findById(dto.getSalesOrderId()));
        DeliveryNote savedDeliveryNote = deliveryNoteRepository.save(existingDeliveryNote);
        return deliveryNoteMapper.toDto(savedDeliveryNote);
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        DeliveryNote deliveryNote = findById(id);
        deliveryNoteRepository.delete(deliveryNote);
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Delivery note deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<DeliveryNoteResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Page<DeliveryNote> deliveryNotes = deliveryNoteRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField)));
        return deliveryNotes.map(deliveryNoteMapper::toDto);
    }
}
