package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.DeliveryNoteItemRequestDto;
import com.crm.dto.responseDtos.DeliveryNoteItemResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.DeliveryNoteItemMapper;
import com.crm.model.DeliveryNoteItem;
import com.crm.repository.DeliveryNoteItemRepository;
import com.crm.service.DeliveryNoteItemService;
import com.crm.service.DeliveryNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryNoteItemServiceImp implements DeliveryNoteItemService {

    private final DeliveryNoteItemRepository deliveryNoteItemRepository;
    private final DeliveryNoteItemMapper deliveryNoteItemMapper;
    private final DeliveryNoteService deliveryNoteService;

    @Override
    public DeliveryNoteItem findById(Long id) {
        return deliveryNoteItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Delivery Note Item not found with id: " + id));
    }

    @Override
    public DeliveryNoteItemResponseDto create(DeliveryNoteItemRequestDto dto) {

        DeliveryNoteItem deliveryNoteItem = deliveryNoteItemMapper.toEntity(dto);

        deliveryNoteItem.setDeliveryNote(deliveryNoteService.findById(dto.getDeliveryNoteId()));

        DeliveryNoteItem savedItem = deliveryNoteItemRepository.save(deliveryNoteItem);
        return deliveryNoteItemMapper.toDto(savedItem);

    }

    @Override
    public DeliveryNoteItemResponseDto find(Long id) {
        return deliveryNoteItemMapper.toDto(findById(id));
    }

    @Override
    public DeliveryNoteItemResponseDto update(Long id, DeliveryNoteItemRequestDto dto) {
        DeliveryNoteItem existingItem = findById(id);

        deliveryNoteItemMapper.updateEntityFromDto(dto, existingItem);

        if (dto.getDeliveryNoteId() != null) {
            existingItem.setDeliveryNote(deliveryNoteService.findById(dto.getDeliveryNoteId()));
        }

        DeliveryNoteItem updatedItem = deliveryNoteItemRepository.save(existingItem);
        return deliveryNoteItemMapper.toDto(updatedItem);
    }

    @Override
    public DeleteResponseDto delete(Long id) {

        DeliveryNoteItem item = findById(id);
        deliveryNoteItemRepository.delete(item);
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Delivery Note Item deleted successfully");
        return deleteResponseDto;

    }

    @Override
    public Page<DeliveryNoteItemResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<DeliveryNoteItem> deliveryNoteItems = deliveryNoteItemRepository.findAll(pageable);
        return deliveryNoteItems.map(deliveryNoteItemMapper::toDto);
    }
}
