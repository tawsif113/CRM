package com.crm.controller;


import com.crm.dto.ApiResponse;
import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.dto.responseDtos.OpportunityStatsResponse;
import com.crm.service.OpportunityService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> createOpportunity(@RequestBody OpportunityRequestDto opportunityRequestDto) {


            OpportunityResponseDto responseDto = opportunityService.create(opportunityRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Opportunity created successfully",responseDto));


    }

    @GetMapping
    public ResponseEntity<?> getAllOpportunities(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "desc") Sort.Direction direction ,
            @RequestParam (value = "sort", defaultValue = "created At") String sortField
    ) {

            return ResponseEntity.ok(ApiResponse.success("Opportunities retrieved successfully", opportunityService.findAll(page, size, direction, sortField)));

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOpportunityById(@PathVariable Long id) {
            OpportunityResponseDto responseDto = opportunityService.find(id);
            return ResponseEntity.ok(ApiResponse.success("Opportunity retrieved successfully", responseDto));

    }
    @PatchMapping("/{id}")
    public ResponseEntity<?>UpdateOpportunity(@PathVariable(value= "id") Long id,
                                              @RequestBody OpportunityRequestDto opportunityRequestDto) {

            OpportunityResponseDto responseDto = opportunityService.update(id, opportunityRequestDto);
            return ResponseEntity.ok(ApiResponse.success("Opportunity updated successfully", responseDto));


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOpportunity(@PathVariable Long id) {

            return ResponseEntity.ok(ApiResponse.success("Opportunity deleted successfully", opportunityService.delete(id)));

    }

    @GetMapping("/stats")
    public ResponseEntity<?> getOpportunityStats() {
           OpportunityStatsResponse opportunityStatsResponse= opportunityService.getStats();
          return  ResponseEntity.status(200).body(ApiResponse.success("Opportunity stats retrieved successfully", opportunityStatsResponse));

    }


}
