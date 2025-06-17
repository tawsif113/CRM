package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.PaymentTermsRequestDto;
import com.crm.dto.responseDtos.PaymentTermsResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.PaymentTermsMapper;
import com.crm.model.PaymentTerms;
import com.crm.repository.PaymentTermsRepository;
import com.crm.service.PaymentTermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTermsServiceImp implements PaymentTermsService {

    private final PaymentTermsRepository paymentTermsRepository;
    private final PaymentTermsMapper paymentTermsMapper;

    @Override
    public PaymentTermsResponseDto create(PaymentTermsRequestDto dto) {

        PaymentTerms paymentTerms = paymentTermsMapper.toEntity(dto);
        PaymentTerms savedPaymentTerms = paymentTermsRepository.save(paymentTerms);
        return paymentTermsMapper.toDto(savedPaymentTerms);
    }

    @Override
    public PaymentTermsResponseDto find(Long id) {

        PaymentTerms paymentTerms = paymentTermsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment Terms not found with id: " + id));
        return paymentTermsMapper.toDto(paymentTerms);
    }

    @Override
    public PaymentTermsResponseDto update(Long id, PaymentTermsRequestDto dto) {

        PaymentTerms paymentTerms = paymentTermsRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment Terms not found with id: " + id));
        paymentTermsMapper.updateEntity(dto, paymentTerms);
        return paymentTermsMapper.toDto(paymentTermsRepository.save(paymentTerms));
    }

    @Override
    public DeleteResponseDto delete(Long id) {

        PaymentTerms paymentTerms = paymentTermsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment Terms not found with id: " + id));
        paymentTermsRepository.delete(paymentTerms);
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Payment Terms deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<PaymentTermsResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {

        Page<PaymentTerms> paymentTermsPage = paymentTermsRepository.findAll(
                PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField)));
        return paymentTermsPage.map(paymentTermsMapper::toDto);
    }

    @Override
    public PaymentTerms findById(Long id) {
        return paymentTermsRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment Terms not found with id: " + id));
    }
}
