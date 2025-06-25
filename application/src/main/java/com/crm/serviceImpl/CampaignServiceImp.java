package com.crm.serviceImpl;

import com.crm.dto.DeleteResponseDto;
import com.crm.dto.requestDtos.CampaignRequestDto;
import com.crm.dto.responseDtos.CampaignResponseDto;
import com.crm.exception.NotFoundException;
import com.crm.mapper.CampaignMapper;
import com.crm.model.Campaign;
import com.crm.model.Territory;
import com.crm.repository.CampaignRepository;
import com.crm.service.CampaignService;
import com.crm.service.TerritoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignServiceImp implements CampaignService {

    private final CampaignMapper campaignMapper;
    private final CampaignRepository campaignRepository;
    private final TerritoryService territoryService;

    @Override
    public CampaignResponseDto create(CampaignRequestDto dto) {
        Territory territory = territoryService.findById(dto.getTerritoryId());
        Campaign campaign = campaignMapper.toEntity(dto);
        campaign.setTerritory(territory);
        return campaignMapper.toDto(campaignRepository.save(campaign));
    }

    @Override
    public CampaignResponseDto find(Long id) {
        return campaignMapper.toDto(findById(id));
    }

    @Override
    public CampaignResponseDto update(Long id, CampaignRequestDto dto) {

        return campaignRepository.findById(id).map(campaign -> {
            campaignMapper.updateEntity(dto, campaign);
            Territory territory = territoryService.findById(dto.getTerritoryId());
            campaign.setTerritory(territory);
            return campaignMapper.toDto(campaignRepository.save(campaign));
        }).orElseThrow(() -> new NotFoundException("Campaign not found"));

    }

    @Override
    public DeleteResponseDto delete(Long id) {
        campaignRepository.delete(findById(id));
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setId(id);
        deleteResponseDto.setMessage("Campaign deleted successfully");
        return deleteResponseDto;
    }

    @Override
    public Page<CampaignResponseDto> findAll(int pageNumber, int pageSize, Sort.Direction direction, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, sortField);
        Page<Campaign> categories = campaignRepository.findAll(pageable);
        return categories.map(campaignMapper::toDto);
    }

    @Override
    public Campaign findById(Long salesCampaign) {
        return campaignRepository.findById(salesCampaign).orElseThrow(()->new NotFoundException("Campaign not found"));
    }
}
