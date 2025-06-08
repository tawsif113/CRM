package com.crm.controller;


import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.dto.responseDtos.OpportunityStatsResponse;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.OpportunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    @PostMapping
    public ResponseEntity<ApiResponse<OpportunityResponseDto>> createOpportunity(@RequestBody OpportunityRequestDto opportunityRequestDto) {


            OpportunityResponseDto responseDto = opportunityService.create(opportunityRequestDto);
            return ApiResponseBuilder.success( responseDto, "Opportunity created successfully");


    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<OpportunityResponseDto>>> getAllOpportunities(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "desc") Sort.Direction direction ,
            @RequestParam (value = "sort", defaultValue = "created At") String sortField
    ) {

            return ApiResponseBuilder.success( opportunityService.findAll(page, size, direction, sortField), "Opportunities retrieved successfully");

    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OpportunityResponseDto>>  getOpportunityById(@PathVariable Long id) {
            OpportunityResponseDto responseDto = opportunityService.find(id);
            return ApiResponseBuilder.success( responseDto, "Opportunity retrieved successfully");

    }
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<OpportunityResponseDto>> UpdateOpportunity(@PathVariable(value= "id") Long id,
                                              @RequestBody OpportunityRequestDto opportunityRequestDto) {

            OpportunityResponseDto responseDto = opportunityService.update(id, opportunityRequestDto);
            return ApiResponseBuilder.success( responseDto, "Opportunity updated successfully");


    }
    @DeleteMapping("/{id}")
    public ResponseEntity< ApiResponse<DeleteResponseDto>> deleteOpportunity(@PathVariable Long id) {

            return ApiResponseBuilder.success( opportunityService.delete(id), "Opportunity deleted successfully");

    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<OpportunityStatsResponse>>  getOpportunityStats() {
           OpportunityStatsResponse opportunityStatsResponse= opportunityService.getStats();
          return   ApiResponseBuilder.success(opportunityStatsResponse, "Opportunity stats retrieved successfully");

    }


}
