package com.freelanceworld.hiring.domain.repository;

import com.freelanceworld.hiring.domain.model.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostulationRepository extends JpaRepository<Postulation, Long> {
    List<Postulation> findAllByOfferId(Long offerId);
    List<Postulation> findAllByFreelancerId(Long freelancerId);
    Optional<Postulation> findByIdAndOfferId(Long postulationId, Long id);
}
