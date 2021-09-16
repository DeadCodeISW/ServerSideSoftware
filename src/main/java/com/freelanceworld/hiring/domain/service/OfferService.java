package com.freelanceworld.hiring.domain.service;

import com.freelanceworld.hiring.domain.model.Offer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfferService {
    List<Offer> getAllOffersByEmployerId(Long employerId);
    List<Offer> getAllOffers();
    Offer getOfferById(Long id);
    List<Offer> getAllOffersBySpecialities(List<Long> specialitiesId);
    Offer activateOffer(Long employerId, Long id);
    Offer deactivateOffer(Long employerId, Long id);
    Offer getOfferByIdAndEmployerId(Long employerId, Long id);
    Offer CreateOffer(Long employerId, Offer offer, Long specialtyId);
    Offer updateOffer(Long employerId, Long id, Offer offerDetails);
    ResponseEntity<?> deleteOffer(Long offerId, Long employerId);
}
