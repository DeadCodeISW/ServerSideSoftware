package com.freelanceworld.locations.service;
import com.freelanceworld.common.exception.ResourceNotFoundException;
import com.freelanceworld.locations.domain.model.District;
import com.freelanceworld.locations.domain.model.Province;
import com.freelanceworld.locations.domain.repository.DistrictRepository;
import com.freelanceworld.locations.domain.repository.ProvinceRepository;
import com.freelanceworld.locations.domain.repository.RegionRepository;
import com.freelanceworld.locations.domain.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Province createProvinceByRegionId(Long regionId, Province province) {
        return regionRepository.findById(regionId).map(
                region -> {
                    province.setRegion(region);
                    return provinceRepository.save(province);
                }
        )
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Region","Id", regionId
                ));
    }

    @Override
    public Province getProvinceByIdAndRegionId(Long regionId,Long provinceId) {
        return provinceRepository.findByIdAndRegionId(regionId,provinceId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Region","Id", regionId
                ));
    }

    @Override
    public List<Province> getAllProvincesByRegionId(Long regionId) {
        return provinceRepository.findAllByRegionId(regionId);
    }

    @Override
    public Province updateProvince(Long regionId,Long provinceId, Province provinceRequest) {
        if(!regionRepository.existsById(regionId))
            throw new ResourceNotFoundException("Province", "Id", regionId);
        return provinceRepository.findById(provinceId).map(
                province -> {
                    province.setName(provinceRequest.getName());
                    return provinceRepository.save(province);
                }
        ).orElseThrow(()->new ResourceNotFoundException("Province","Id",provinceId));
    }

    @Override
    public ResponseEntity<?> deleteProvince(Long regionId, Long provinceId) {
        if(!regionRepository.existsById(regionId)) {
            throw new ResourceNotFoundException(
                    "Region", "Id", regionId
            );
        }

        List<District> districtList = districtRepository.findAllByProvinceId(provinceId);

        if(districtList!=null){
            for (District district:districtList){
                districtRepository.delete(district);
            }
        }

        return provinceRepository.findById(provinceId).map(
                province -> {
                    provinceRepository.delete(province);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Province", "Id",provinceId ));
    }

}
