package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.TerritoryRequestDto;
import com.crm.dto.responseDtos.TerritoryResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.TerritoryMapper;
import com.crm.model.SalesPerson;
import com.crm.model.Territory;
import com.crm.repository.TerritoryRepository;
import com.crm.service.SalesPersonService;
import com.crm.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TerritoryServiceImp implements TerritoryService {

    private final TerritoryMapper territoryMapper;
    private final TerritoryRepository territoryRepository;
    private final SalesPersonService salesPersonService;

    @Override
    public TerritoryResponseDto create(TerritoryRequestDto dto) {
        SalesPerson salesPerson = salesPersonService.findById(dto.getTerritoryManagerId());
        Territory territory = territoryMapper.toEntity(dto);
        territory.setTerritoryManager(salesPerson);
        return territoryMapper.toDto(territoryRepository.save(territory));
    }

    @Override
    public TerritoryResponseDto find(Long id) {
        return territoryMapper
                .toDto(findById(id));
    }

    @Override
    public TerritoryResponseDto update(Long id, TerritoryRequestDto dto) {
        return territoryRepository.findById(id)
                .map(territory -> {
                    territoryMapper.updateEntity(dto, territory);
                    SalesPerson salesPerson = salesPersonService.findById(dto.getTerritoryManagerId());
                    territory.setTerritoryManager(salesPerson);
                    return territoryMapper.toDto(territoryRepository.save(territory));
                })
                .orElseThrow(() -> new NotFoundException("Territory not found"));
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        territoryRepository.delete(territoryRepository
                .findById(id).orElseThrow(() -> new NotFoundException("Territory not found")));
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Territory deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<TerritoryResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<Territory> categories = territoryRepository.findAll(pageable);
        return categories.map(territoryMapper::toDto);
    }

    @Override
    public Territory findById(Long territoryId) {
        return territoryRepository
                .findById(territoryId)
                .orElseThrow(() -> new NotFoundException("Territory with ID " + territoryId + " does not exist"));
    }
}
