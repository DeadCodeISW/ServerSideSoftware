package com.freelanceworld.locations.controller;

import com.freelanceworld.locations.domain.model.District;
import com.freelanceworld.locations.domain.service.DistrictService;
import com.freelanceworld.locations.resource.District.DistrictResource;
import com.freelanceworld.locations.resource.District.SaveDistrictResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DistrictController {

    @Autowired
    private DistrictService districtService;
    @Autowired
    private ModelMapper mapper;
    @Operation(summary = "Get Districts", description = "Get Districts",
            tags = "District")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/{provinceId}/districts")
    public List<DistrictResource> getAllDistrictsByProvinceId(@PathVariable Long provinceId){
        return districtService.getAllDistrictsByProvinceId(provinceId).stream().map(this::convertToResource).collect(Collectors.toList());
    }
    @Operation(summary = "Get District", description = "Get District by provinceId",
            tags = "District")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve a Postulations by provinceId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/{provinceId}/district/{districtId}")
    public DistrictResource getDistrictByIdAndProvinceId(@PathVariable Long provinceId,@PathVariable Long districtId){
        Optional<District> district = districtService.getDistrictByIdAndProvinceId(districtId,provinceId);
        return district.map(this::convertToResource).orElse(null);
    }
    @Operation(summary = "Create District", description = "Create District",
            tags = "District")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("/{provinceId}/districts")
    public DistrictResource createDistrict(@PathVariable Long provinceId, @RequestBody SaveDistrictResource districtResource){
        District district = convertToEntity(districtResource);
        return convertToResource(districtService.createDistrictByProvinceId(provinceId,district));
    }
    @Operation(summary = "Update District", description = "Update District",
            tags = "District")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @PutMapping("/{provinceId}/districts/{districtId}")
    public DistrictResource updateDistrict(@PathVariable Long provinceId,@PathVariable Long districtId,@RequestBody SaveDistrictResource districtResource){
        District district = convertToEntity(districtResource);
        return convertToResource(districtService.updateDistrict(provinceId,districtId,district));
    }
    @Operation(summary = "Delete District", description = "Delete District",
            tags = "District")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @DeleteMapping("{provinceId}/districts/{districtId}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long provinceId,@PathVariable Long districtId){
        return districtService.deleteDistrict(provinceId,districtId);
    }

    private District convertToEntity(SaveDistrictResource resource){
        return mapper.map(resource, District.class);
    }

    private DistrictResource convertToResource(District entity){
        return mapper.map(entity, DistrictResource.class);
    }
}
