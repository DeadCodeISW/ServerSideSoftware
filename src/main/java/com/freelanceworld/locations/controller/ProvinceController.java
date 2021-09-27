package com.freelanceworld.locations.controller;

import com.freelanceworld.locations.domain.model.Province;
import com.freelanceworld.locations.domain.service.ProvinceService;
import com.freelanceworld.locations.resource.Province.ProvinceResource;
import com.freelanceworld.locations.resource.Province.SaveProvinceResource;
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
@RequestMapping("/api")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Provinces", description = "Get Provinces",
            tags = "Province")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("{regionId}/provinces")
    public List<ProvinceResource> getAllProvincesByRegionId(@PathVariable Long regionId){
        return provinceService.getAllProvincesByRegionId(regionId).stream().map(this::convertToResource).collect(Collectors.toList());
    }
    @Operation(summary = "Get Province", description = "Get Province by provinceId",
            tags = "Province")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve a Postulations by provinceId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("{regionId}/provinces/{provinceId}")
    public ProvinceResource getProvinceByIdAndRegionId(@PathVariable Long regionId,@PathVariable Long provinceId){
        return convertToResource(provinceService.getProvinceByIdAndRegionId(regionId,provinceId));
    }

    @Operation(summary = "Create Province", description = "Create Province",
            tags = "Province")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("{regionId}/provinces")
    public ProvinceResource createProvince(@PathVariable Long regionId,@RequestBody SaveProvinceResource provinceResource){
        Province province = convertToEntity(provinceResource);
        return convertToResource(provinceService.createProvinceByRegionId(regionId,province));
    }
    @Operation(summary = "Update Province", description = "Update Province",
            tags = "Province")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @PutMapping("{regionId}/provinces/{provinceId}")
    public ProvinceResource updateProvince(@PathVariable Long regionId,@PathVariable Long provinceId,@RequestBody SaveProvinceResource provinceResource){
        Province province = convertToEntity(provinceResource);
        return convertToResource(provinceService.updateProvince(regionId,provinceId,province));
    }
    @Operation(summary = "Delete Province", description = "Delete Province",
            tags = "Province")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @DeleteMapping("{regionId}/provinces/{provinceId}")
    public ResponseEntity<?> deleteProvince(@PathVariable Long regionId,@PathVariable Long provinceId){
        return provinceService.deleteProvince(regionId,provinceId);
    }

    private Province convertToEntity(SaveProvinceResource resource){
        return mapper.map(resource, Province.class);
    }

    private ProvinceResource convertToResource(Province entity){
        return mapper.map(entity, ProvinceResource.class);
    }
}
