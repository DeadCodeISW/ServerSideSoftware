package com.freelanceworld.hiring.service;

import com.freelanceworld.common.exception.ResourceNotFoundException;
import com.freelanceworld.hiring.domain.model.Requirement;
import com.freelanceworld.hiring.domain.repository.OfferRepository;
import com.freelanceworld.hiring.domain.repository.RequirementRepository;
import com.freelanceworld.hiring.domain.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Override
    public List<Requirement> getAllRequirementsByOfferId(Long offerId) {
        return requirementRepository.findAllByOfferId(offerId);
    }

    @Override
    public Requirement getRequirementByIdAndOfferId(Long offerId, Long id) {
        if(!offerRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );
        return requirementRepository.findByIdAndOfferId(offerId,id).orElseThrow(() -> new ResourceNotFoundException(
                "Requirement","Id", id
        ));
    }

    @Override
    public Requirement createRequirement(Long offerId, Requirement requirement) {
        return offerRepository.findById(offerId).map(offer -> {
            requirement.setOffer(offer);
            return requirementRepository.save(requirement);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Offer", "Id", offerId
        ));
    }

    @Override
    public Requirement updateRequirement(Long offerId, Long id, Requirement requirementDetails) {
        if(!offerRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );

        return requirementRepository.findById(id).map(requirement -> {
            requirement.setName(requirementDetails.getName());
            requirement.setLevel(requirementDetails.getLevel());
            return requirementRepository.save(requirement);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Requirement","Id", id
        ));
    }

    @Override
    public ResponseEntity<?> deleteRequirement(Long offerId, Long id) {
        if(!offerRepository.existsById(offerId))
            throw new ResourceNotFoundException(
                    "Offer","Id", offerId
            );
        return requirementRepository.findById(id).map(requirement -> {
            requirementRepository.delete(requirement);
            return ResponseEntity.ok().build();
        }) .orElseThrow(() -> new ResourceNotFoundException(
                "Requirement","Id", id
        ));
    }
}
