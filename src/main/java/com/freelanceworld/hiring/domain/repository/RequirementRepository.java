package com.freelanceworld.hiring.domain.repository;

import com.freelanceworld.hiring.domain.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {
    List<Requirement> findAllByOfferId(Long offerId);
    Optional<Requirement> findByIdAndOfferId(Long offerId, Long id);
}
