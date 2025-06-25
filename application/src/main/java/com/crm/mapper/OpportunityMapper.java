package com.crm.mapper;

import com.crm.dto.requestDtos.OpportunityRequestDto;
import com.crm.dto.responseDtos.OpportunityItemResponseDto;
import com.crm.dto.responseDtos.OpportunityResponseDto;
import com.crm.model.Opportunity;
import com.crm.model.OpportunityItem;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OpportunityMapper {


    @Mapping(target = "lead", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "nextContactBy", ignore = true)
    @Mapping(target = "opportunityOwner", ignore = true)
    @Mapping(target = "salesCampaign", ignore = true)
    @Mapping(target = "items", ignore = true)
    Opportunity toEntity(OpportunityRequestDto dto);

    @Mapping(source = "lead.id", target = "leadId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "nextContactBy.id", target = "nextContactBy")
    @Mapping(source = "opportunityOwner.id", target = "opportunityOwner")
    @Mapping(source = "salesCampaign.id", target = "salesCampaign")
    @Mapping(source = "items", target = "items", qualifiedByName = "itemListToDtoList")
    OpportunityResponseDto toDto(Opportunity opp);



    @Named("itemToDto")
    @Mappings({
            @Mapping(source = "item.id",       target = "itemId"),
            @Mapping(source = "item.itemName", target = "itemName"),
            @Mapping(source = "quantity",      target = "quantity"),
    })
    OpportunityItemResponseDto itemToDto(OpportunityItem item);



    // Convert a list of OpportunityItem to a list of OpportunityItemResponseDto
    @Named("itemListToDtoList")
    default List<OpportunityItemResponseDto> itemListToDtoList(List<OpportunityItem> items) {
        if (items == null) {
            return null;
        }
        return items.stream()
                .map(this::itemToDto)
                .collect(Collectors.toList());
    }

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity( OpportunityRequestDto  opportunityRequestDto , @MappingTarget Opportunity  opportunity );


}
