package com.crm.controller;


import com.crm.dto.ApiResponse;
import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
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

        try {
            OpportunityResponseDto responseDto = opportunityService.create(opportunityRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Opportunity created successfully",responseDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }

    }

    @GetMapping
    public ResponseEntity<?> getAllOpportunities(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "desc") Sort.Direction direction ,
            @RequestParam (value = "sort", defaultValue = "created At") String sortField
    ) {
        try {
            return ResponseEntity.ok(ApiResponse.success("Opportunities retrieved successfully", opportunityService.findAll(page, size, direction, sortField)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOpportunityById(@PathVariable Long id) {
        try {
            OpportunityResponseDto responseDto = opportunityService.find(id);
            return ResponseEntity.ok(ApiResponse.success("Opportunity retrieved successfully", responseDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?>UpdateOpportunity(@PathVariable(value= "id") Long id,
                                              @RequestBody OpportunityRequestDto opportunityRequestDto) {
        try {
            OpportunityResponseDto responseDto = opportunityService.update(id, opportunityRequestDto);
            return ResponseEntity.ok(ApiResponse.success("Opportunity updated successfully", responseDto));
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOpportunity(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ApiResponse.success("Opportunity deleted successfully", opportunityService.delete(id)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
        }
    }


}
