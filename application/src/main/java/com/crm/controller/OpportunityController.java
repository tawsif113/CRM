package com.crm.controller;


import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.service.OpportunityService;
import com.crm.serviceImpl.OpportunityServiceImp;
import lombok.RequiredArgsConstructor;
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
    public String createOpportunity(@RequestBody OpportunityRequestDto opportunityRequestDto) {

        OpportunityResponseDto resp = opportunityService.create(opportunityRequestDto);

        return "Opportunity created successfully";
    }
}
