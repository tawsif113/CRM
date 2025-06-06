package com.crm.service;

import com.crm.dto.BaseDto;
import com.crm.dto.DeleteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
///  * BaseService interface that defines common operations for services in the CRM application.
 /*
  @param <E> the type of the response DTO
  @param <D> the type of the request DTO
 */
public interface BaseService<E extends BaseDto,D> {
    E create(D dto);

    E find(Long id);

    E update(Long id, D dto);

    DeleteResponseDto delete(Long id);

    Page<E> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField);
}
