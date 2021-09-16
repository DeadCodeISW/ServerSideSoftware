package com.freelanceworld.locations.service;

import com.freelanceworld.common.exception.ResourceNotFoundException;
import com.freelanceworld.locations.domain.model.District;
import com.freelanceworld.locations.domain.repository.DistrictRepository;
import com.freelanceworld.locations.domain.repository.ProvinceRepository;
import com.freelanceworld.locations.domain.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;


    @Override
    public District createDistrictByProvinceId(Long provinceId, District district) {
        return provinceRepository.findById(provinceId).map(
                province -> {
                    district.setProvince(province);
                    return districtRepository.save(district);
                }
        )
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Province", "Id", provinceId
                ));
    }

    @Override
    public Optional<District> getDistrictByIdAndProvinceId(Long provinceId, Long districtId) {
        return districtRepository.findByIdAndProvinceId(provinceId,districtId);
                //.orElseThrow(()->new ResourceNotFoundException(
                //        "Province","Id",provinceId
                //));
    }

    @Override
    public List<District> getAllDistrictsByProvinceId(Long provinceId) {
        return districtRepository.findAllByProvinceId(provinceId);
    }

    @Override
    public District updateDistrict(Long provinceId, Long districtId, District district) {
        if(!provinceRepository.existsById(provinceId))
            throw new ResourceNotFoundException("Province", "Id", provinceId);
        return districtRepository.findById(districtId).map(
                district1 -> {
                    district1.setDistrictname(district.getDistrictname());
                    return districtRepository.save(district1);
                }
        ).orElseThrow(()->new ResourceNotFoundException("District","Id",districtId));
    }

    @Override
    public ResponseEntity<?> deleteDistrict(Long provinceId, Long districtId) {
        if(!provinceRepository.existsById(provinceId)) {
            throw new ResourceNotFoundException(
                    "Province", "Id", provinceId
            );
        }
        return districtRepository.findById(districtId).map(
                district -> {
                    districtRepository.delete(district);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("District", "Id",districtId ));
    }
}
