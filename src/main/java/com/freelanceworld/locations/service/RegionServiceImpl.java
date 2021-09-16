package com.freelanceworld.locations.service;

import com.freelanceworld.common.exception.ResourceNotFoundException;
import com.freelanceworld.locations.domain.model.District;
import com.freelanceworld.locations.domain.model.Province;
import com.freelanceworld.locations.domain.model.Region;
import com.freelanceworld.locations.domain.repository.DistrictRepository;
import com.freelanceworld.locations.domain.repository.ProvinceRepository;
import com.freelanceworld.locations.domain.repository.RegionRepository;
import com.freelanceworld.locations.domain.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Optional<Region> getRegionById(Long regionId) {
        return regionRepository.findById(regionId);
                //.orElseThrow(()-> new ResourceNotFoundException(
                //        "Region", "Id", regionId
                //));
    }

    @Override
    public ArrayList<Region> getAllRegions() {
        return (ArrayList<Region>) regionRepository.findAll();
    }

    @Override
    public Region updateRegion(Long regionId, Region region) {
        return regionRepository.findById(regionId).map(
                Region -> {
                    Region.setName(region.getName());
                    return regionRepository.save(Region);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Region", "Id", region));
    }

    @Override
    public ResponseEntity<?> deleteRegion(Long regionId) {
        Region existed = regionRepository.findById(regionId)
                .orElseThrow(()-> new ResourceNotFoundException("Region","Id",regionId));

        List<Province> provinceList = provinceRepository.findAllByRegionId(regionId);

        if (provinceList!=null){
            for(Province province: provinceList){
                Long provinceId = province.getId();
                List<District> districtList = districtRepository.findAllByProvinceId(provinceId);
                if(districtList!=null){
                    for (District district: districtList){
                        districtRepository.delete(district);
                    }
                }
                provinceRepository.delete(province);
            }
        }

        regionRepository.delete(existed);
        return ResponseEntity.ok().build();
    }
}
