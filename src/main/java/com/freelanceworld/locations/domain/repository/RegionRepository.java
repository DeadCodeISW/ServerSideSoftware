package com.freelanceworld.locations.domain.repository;

import com.freelanceworld.locations.domain.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long> {
    //Region FindByRegionName(String regionname);
}
