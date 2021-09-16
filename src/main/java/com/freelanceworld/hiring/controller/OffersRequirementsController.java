package com.freelanceworld.hiring.controller;

import com.freelanceworld.hiring.domain.model.Requirement;
import com.freelanceworld.hiring.domain.service.RequirementService;
import com.freelanceworld.hiring.resource.requirement.RequirementResource;
import com.freelanceworld.hiring.resource.requirement.SaveRequirementResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
public class OffersRequirementsController {
    @Autowired
    private RequirementService requirementService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Requirements", description = "Get All Requirements by offerId",
            tags = "Offer-Requirements")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Requirements by offerId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/{offerId}/requirements")
    public List<RequirementResource> getAllOffers(@PathVariable Long offerId){
        List<Requirement> requirements = requirementService.getAllRequirementsByOfferId(offerId);
        return requirements.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Operation(summary = "Get Requirement", description = "Get Requirements by offerId and requirementId",
            tags = "Offer-Requirements")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should a Requirements by offerId and requirementId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/{offerId}/requirements/{requirementsId}")
    public RequirementResource getRequirementById(@PathVariable Long offerId,@PathVariable Long requirementsId){
        Requirement requirement = requirementService.getRequirementByIdAndOfferId(offerId, requirementsId);
        return convertToResource(requirement);
    }

    @Operation(summary = "Create Requirement", description = "Create Requirement by offerId",
            tags = "Offer-Requirements")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should create an requirement",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("/{offerId}/requirements")
    public RequirementResource createRequirement(@PathVariable Long offerId,@RequestBody SaveRequirementResource resource){
        Requirement requirement = requirementService.createRequirement(offerId, convertToEntity(resource));
        return convertToResource(requirement);
    }

    @Operation(summary = "Update Requirement", description = "Update Requirement",
            tags = "Offer-Requirements")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should update a Requirement",
            content = @Content(mediaType = "application/json"))})
    @PutMapping("/{offerId}/requirements/{requirementsId}")
    public RequirementResource updateRequirement(@PathVariable Long offerId,@PathVariable Long requirementsId,@RequestBody SaveRequirementResource resource){
        Requirement requirement = requirementService.updateRequirement(offerId, requirementsId, convertToEntity(resource));
        return convertToResource(requirement);
    }

    @Operation(summary = "Delete Requirement", description = "Delete Requirement",
            tags = "Offer-Requirements")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should delete a Requirement",
            content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/{offerId}/requirements/{requirementsId}")
    public ResponseEntity<?> deleteRequirement(@PathVariable Long offerId, @PathVariable Long requirementsId){
        return requirementService.deleteRequirement(offerId, requirementsId);
    }


    private Requirement convertToEntity(SaveRequirementResource resource){
        return mapper.map(resource, Requirement.class);
    }

    private RequirementResource convertToResource(Requirement entity){
        return mapper.map(entity, RequirementResource.class);
    }
}
