package com.crm.controller;

import com.crm.dto.CampaignDto;
import com.crm.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crm/api/v1/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    public ResponseEntity<CampaignDto> createCampaign(CampaignDto campaignDto) {
        return ResponseEntity.ok(campaignService.create(campaignDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDto> getCampaign(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(campaignService.find(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CampaignDto> updateCampaign(@PathVariable(value = "id") Long id, CampaignDto campaignDto) {
        return ResponseEntity.ok(campaignService.update(id, campaignDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable(value = "id") Long id) {
        campaignService.delete(id);
        return ResponseEntity.ok("Campaign deleted successfully");
    }

    @GetMapping
    public ResponseEntity<Page<CampaignDto>> getAllCampaigns(@RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                             @RequestParam(defaultValue = "id") String sortField) {
        return ResponseEntity.ok(campaignService.findAll(pageNumber, pageSize, direction, sortField));
    }
}
