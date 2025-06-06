package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.SalesTargetRequestDto;
import com.crm.dto.responseDtos.SalesTargetResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.SalesTargetMapper;
import com.crm.model.SalesTarget;
import com.crm.repository.SalesTargetRepository;
import com.crm.service.SalesPersonService;
import com.crm.service.SalesTargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesTargetServiceImp implements SalesTargetService {

    private final SalesTargetMapper salesTargetMapper;
    private final SalesTargetRepository salesTargetRepository;
    private final SalesPersonService salesPersonService;


    @Override
    public SalesTargetResponseDto create(SalesTargetRequestDto dto) {
        SalesTarget salesTarget = salesTargetMapper.toEntity(dto);
        salesTarget.setSalesperson(salesPersonService.findById(dto.getSalespersonId()));
        return salesTargetMapper.toDto(salesTargetRepository.save(salesTarget));
    }

    @Override
    public SalesTargetResponseDto find(Long id) {
        return salesTargetRepository.findById(id).map(salesTargetMapper::toDto).orElseThrow(() -> new NotFoundException("Sales Target not found"));
    }

    @Override
    public SalesTargetResponseDto update(Long id, SalesTargetRequestDto dto) {

        SalesTarget salesTarget = salesTargetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sales Target not found"));

        salesTargetMapper.updateEntityFromDto(dto, salesTarget);

        return salesTargetMapper.toDto(salesTargetRepository.save(salesTarget));

    }

    @Override
    public DeleteResponseDto delete(Long id) {
        SalesTarget salesTarget = salesTargetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sales Target not found"));
        salesTargetRepository.delete(salesTarget);
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Sales Target deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<SalesTargetResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Page<SalesTarget> salesTargetPage = salesTargetRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField)));
        return salesTargetPage.map(salesTargetMapper::toDto);
    }
}
