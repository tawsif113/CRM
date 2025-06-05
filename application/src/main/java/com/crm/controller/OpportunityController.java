package com.crm.controller;


import com.crm.dto.ApiResponse;
import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.serviceImpl.OpportunityServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/opportunities")
public class OpportunityController {

    private final OpportunityServiceImp opportunityService;

    @PostMapping
    public ResponseEntity<?> createOpportunity(@RequestBody OpportunityRequestDto opportunityRequestDto) {

        try {
            OpportunityResponseDto responseDto = opportunityService.create(opportunityRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Opportunity created successfully",responseDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }

    }
}
