package com.freelanceworld.locations.controller;

import com.freelanceworld.locations.domain.model.Region;
import com.freelanceworld.locations.domain.service.RegionService;
import com.freelanceworld.locations.resource.Region.RegionResource;
import com.freelanceworld.locations.resource.Region.SaveRegionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RegionController {

    @Autowired
    private RegionService regionService;
    @Autowired
    private ModelMapper mapper;
    @Operation(summary = "Get Regions", description = "Get Regions",
            tags = "Region")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/regions")
    public List<RegionResource> getAllRegions(){
        return regionService.getAllRegions()
                .stream()
                .map(
                        region -> mapper.map(region,RegionResource.class)
                ).collect(Collectors.toList());
    }
    @Operation(summary = "Get Region", description = "Get Region by provinceId",
            tags = "Region")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve a Postulations by provinceId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/regions/{id}")
    public RegionResource getRegionById(@PathVariable Long id){
        Optional<Region> region = regionService.getRegionById(id);
        return region.map(this::convertToResource).orElse(null);
    }
    @Operation(summary = "Create Region", description = "Create Region",
            tags = "Region")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("/regions")
    public RegionResource saveRegion(@Valid @RequestBody SaveRegionResource resource){
        Region region = convertToEntity(resource);
        return convertToResource(regionService.createRegion(region));
    }
    @Operation(summary = "Update Region", description = "Update Region",
            tags = "Region")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @PutMapping("/regions/{id}")
    public RegionResource updateRegion(@PathVariable Long id, @RequestBody SaveRegionResource resource){
        Region region = convertToEntity(resource);
        return convertToResource(regionService.updateRegion(id, region));
    }
    @Operation(summary = "Delete Region", description = "Delete Region",
            tags = "Region")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/regions/{id}")
    public ResponseEntity<?> deleteRegions(@PathVariable Long id){
        return regionService.deleteRegion(id);
    }


    private Region convertToEntity(SaveRegionResource resource){
        return mapper.map(resource, Region.class);
    }

    private RegionResource convertToResource(Region entity){
        return mapper.map(entity, RegionResource.class);
    }
}
