package com.freelanceworld.hiring.domain.service;

import com.freelanceworld.hiring.domain.model.Requirement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RequirementService {
    List<Requirement> getAllRequirementsByOfferId(Long offerId);
    Requirement getRequirementByIdAndOfferId(Long offerId, Long id);
    Requirement createRequirement(Long offerId, Requirement requirement);
    Requirement updateRequirement(Long offerId, Long id, Requirement requirementDetails);
    ResponseEntity<?> deleteRequirement(Long offerId, Long id);
}
