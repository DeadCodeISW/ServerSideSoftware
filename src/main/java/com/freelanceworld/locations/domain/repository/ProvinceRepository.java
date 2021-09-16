package com.freelanceworld.locations.domain.repository;

import com.freelanceworld.locations.domain.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {
    //Province FindByProvinceName(String provincename);
    Optional<Province> findByIdAndRegionId(Long regionId, Long provinceId);
    List<Province> findAllByRegionId(Long regionId);
}
