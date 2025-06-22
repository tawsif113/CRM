package com.crm.controller;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.CampaignRequestDto;
import com.crm.dto.responseDtos.CampaignResponseDto;
import com.crm.middleware.ApiResponseBuilder;
import com.crm.model.ApiResponse;
import com.crm.service.CampaignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    public ResponseEntity<ApiResponse<CampaignResponseDto>> createCampaign(@Valid @RequestBody CampaignRequestDto campaignDto) {
        return ApiResponseBuilder.success(campaignService.create(campaignDto), "Campaign created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CampaignResponseDto>> getCampaign(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(campaignService.find(id), "Campaign retrieved successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CampaignResponseDto>> updateCampaign(@PathVariable(value = "id") Long id, @Valid @RequestBody CampaignRequestDto campaignDto) {
        return ApiResponseBuilder.success(campaignService.update(id, campaignDto), "Campaign updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DeleteResponseDto>> deleteCampaign(@PathVariable(value = "id") Long id) {
        return ApiResponseBuilder.success(campaignService.delete(id), "Campaign deleted successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CampaignResponseDto>>> getAllCampaigns(@RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                             @RequestParam(defaultValue = "id") String sortField) {
        return ApiResponseBuilder.success(campaignService.findAll(pageNumber, pageSize, direction, sortField), "Campaigns retrieved successfully");
    }
}
