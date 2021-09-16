package com.freelanceworld.locations.domain.service;

import com.freelanceworld.locations.domain.model.Province;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProvinceService {
    Province createProvinceByRegionId(Long regionId, Province province);
    Province getProvinceByIdAndRegionId(Long regionId, Long provinceId);
    List<Province> getAllProvincesByRegionId(Long regionId);
    Province updateProvince(Long regionId,Long provinceId, Province province);
    ResponseEntity<?> deleteProvince(Long regionId,Long provinceId);
}
