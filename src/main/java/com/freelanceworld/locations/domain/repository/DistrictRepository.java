package com.freelanceworld.locations.domain.repository;

import com.freelanceworld.locations.domain.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
    //District FindByDistrictName(String districtname);
    List<District> findAllByProvinceId(Long provinceId);
    Optional<District> findByIdAndProvinceId(Long provinceId, Long districtId);
}
