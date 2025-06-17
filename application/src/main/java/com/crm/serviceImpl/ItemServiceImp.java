package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.ItemRequestDto;
import com.crm.dto.responseDtos.ItemResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.ItemMapper;
import com.crm.model.Item;
import com.crm.repository.ItemRepository;
import com.crm.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImp implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemResponseDto create(ItemRequestDto dto) {
        Item item = itemMapper.toEntity(dto);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toDto(savedItem);
    }

    @Override
    public ItemResponseDto find(Long id) {
        return itemMapper.toDto(findById(id));
    }

    @Override
    public ItemResponseDto update(Long id, ItemRequestDto dto) {
        Item existingItem = findById(id);
        itemMapper.updateEntityFromDto(dto, existingItem);
        Item savedItem = itemRepository.save(existingItem);
        return itemMapper.toDto(savedItem);
    }

    @Override
    public DeleteResponseDto delete(Long id) {

        Item item = findById(id);
        itemRepository.delete(item);
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Item deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<ItemResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Page<Item> itemsPage = itemRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField)));
        return itemsPage.map(itemMapper::toDto);
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id: " + id));
    }
}
