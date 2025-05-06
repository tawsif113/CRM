package com.crm.service;

import com.crm.dto.BaseDto;
import com.crm.dto.DeleteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface BaseService<D extends BaseDto> {
    D create(D dto);

    D find(Long id);

    D update(Long id, D dto);

    DeleteResponseDto delete(Long id);

    Page<D> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField);
}
