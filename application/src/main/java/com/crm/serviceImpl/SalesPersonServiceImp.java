package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.SalesPersonRequestDto;
import com.crm.dto.responseDtos.SalesPersonResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.SalesPersonMapper;
import com.crm.model.SalesPerson;
import com.crm.repository.SalesPersonRepository;
import com.crm.service.SalesPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesPersonServiceImp implements SalesPersonService {

    private final SalesPersonMapper salesPersonMapper;
    private final SalesPersonRepository salesPersonRepository;


    @Override
    public SalesPersonResponseDto create(SalesPersonRequestDto dto) {
        return salesPersonMapper.toDto(salesPersonRepository.save(salesPersonMapper.toEntity(dto)));
    }

    @Override
    public SalesPersonResponseDto find(Long id) {
        return salesPersonRepository.findById(id)
                .map(salesPersonMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Sales Person not found with id: " + id));
    }

    @Override
    public SalesPersonResponseDto update(Long id, SalesPersonRequestDto dto) {

        SalesPerson salesPerson = salesPersonRepository.findById(id).orElseThrow(()-> new NotFoundException("Sales Person not found with id: " + id));
        salesPersonMapper.updateEntity(dto, salesPerson);
        return salesPersonMapper.toDto(salesPersonRepository.save(salesPerson));
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        salesPersonRepository.delete(salesPersonRepository.findById(id).orElseThrow(() -> new NotFoundException("Sales Person not found with id: " + id)));
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Sales Person deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<SalesPersonResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Page<SalesPerson> salesPersonPage = salesPersonRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField)));
        return salesPersonPage.map(salesPersonMapper::toDto);
    }

    @Override
    public SalesPerson findById(Long id) {
        return salesPersonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sales Person not found with id: " + id));
    }
}
