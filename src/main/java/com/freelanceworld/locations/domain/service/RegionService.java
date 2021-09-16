package com.freelanceworld.locations.domain.service;

import com.freelanceworld.locations.domain.model.Region;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RegionService {
    Region createRegion(Region region);
    Optional<Region> getRegionById(Long regionId);
    List<Region> getAllRegions();
    Region updateRegion(Long regionId,Region region);
    ResponseEntity<?> deleteRegion(Long regionId);
}
