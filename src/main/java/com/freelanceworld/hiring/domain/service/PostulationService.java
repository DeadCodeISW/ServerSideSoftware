package com.freelanceworld.hiring.domain.service;

import com.freelanceworld.hiring.domain.model.Postulation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PostulationService {
    List<Postulation> getAllPostulationsByOfferId(Long offerId);
    List<Postulation> getAllPostulationsByFreelancerId(Long offerId);
    Optional<Postulation> getPostulationByIdAndOfferId(Long postulationId, Long id);
    Postulation createPostulation(Long offerId,Long freelancerId, Postulation postulation);
    Postulation updatePostulation(Long postulationId, Long id, Postulation postulationDetails);
    Postulation acceptPostulation(Long offerId, Long id);
    Postulation rejectPostulation(Long offerId, Long id);
    ResponseEntity<?> deletePostulation(Long offerId, Long id);
}
