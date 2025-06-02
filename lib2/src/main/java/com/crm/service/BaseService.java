package com.crm.service;

import com.crm.dto.BaseDto;
import com.crm.dto.DeleteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface BaseService<E extends BaseDto,D> {
    E create(D dto);

    E find(Long id);

    E update(Long id, D dto);

    DeleteResponseDto delete(Long id);

    Page<E> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField);
}
