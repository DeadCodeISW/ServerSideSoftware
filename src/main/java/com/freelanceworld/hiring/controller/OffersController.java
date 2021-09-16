package com.freelanceworld.hiring.controller;
import com.freelanceworld.hiring.domain.model.Offer;
import com.freelanceworld.hiring.domain.service.OfferService;
import com.freelanceworld.hiring.resource.Offer.OfferResource;
import com.freelanceworld.hiring.resource.Offer.SaveOfferResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class OffersController {
    private final OfferService offerService;
    private final ModelMapper mapper;

    @Autowired
    public OffersController(OfferService offerService, ModelMapper mapper) {
        this.offerService = offerService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Offers", description = "Get All Offer",
            tags = "Offers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Offers",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/offers")
    public List<OfferResource> getAllOffers(){
        List<Offer> offers = offerService.getAllOffers();
        return offers.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Operation(summary = "Get Offer By Id", description = "Get Offer",
            tags = "Offers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve an Offer",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/offers/{offerId}")
    public OfferResource getOfferById(@PathVariable Long offerId){
        Offer offer = offerService.getOfferById(offerId);
        return convertToResource(offer);
    }

    @Operation(summary = "Get Offers By EmployerId", description = "Get All Offers by employerId",
            tags = "Employer-Offers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Offers by employerId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/employers/{employerId}/offers")
   public List<OfferResource> getAllOffersByEmployerId(@PathVariable Long employerId){
        List<Offer> offers = offerService.getAllOffersByEmployerId(employerId);
        return offers.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Operation(summary = "Get Offer By employerId and offerId", description = "Get Offer By employerId and offerId",
            tags = "Employer-Offers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve an offer by employerId and offerId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/employers/{employerId}/offers/{offerId}")
    public OfferResource getOfferByEmployerIdAndId(@PathVariable Long employerId, @PathVariable Long offerId){
        Offer offer = offerService.getOfferByIdAndEmployerId(employerId,offerId);
        return convertToResource(offer);
    }

    @Operation(summary = "Create an offer", description = "Create an offer",
            tags = "Employer-Offers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve an offer",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("/employers/{employerId}/offers")
    public OfferResource createNewOffer(@PathVariable Long employerId,@Valid @RequestBody SaveOfferResource resource){
        Offer offer = convertToEntity(resource);
        return convertToResource(offerService.CreateOffer(employerId,offer, resource.getSpecialtyId()));
    }

    @Operation(summary = "Create an offer", description = "Create an offer",
            tags = "Employer-Offers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve an offer",
            content = @Content(mediaType = "application/json"))})
    @PutMapping("/employers/{employerId}/offers/{offerId}")
    public OfferResource updateOffer(@PathVariable Long employerId,@PathVariable Long offerId,@Valid @RequestBody SaveOfferResource resource){
        Offer offer = convertToEntity(resource);
        return convertToResource(offerService.updateOffer(employerId,offerId,offer));
    }
    @Operation(summary = "Activate an offer", description = "Activate an offer",
            tags = "Employer-Offers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should activate an offer",
            content = @Content(mediaType = "application/json"))})
    @PatchMapping("/employers/{employerId}/offers/{offerId}/activate")
    public OfferResource activateOffer(@PathVariable Long employerId,@PathVariable Long offerId){
        return convertToResource(offerService.activateOffer(employerId, offerId));
    }
    @Operation(summary = "Deactivate an offer", description = "Deactivate an offer",
            tags = "Employer-Offers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should deactivate an offer",
            content = @Content(mediaType = "application/json"))})
    @PatchMapping("/employers/{employerId}/offers/{offerId}/deactivate")
    public OfferResource deactivateOffer(@PathVariable Long employerId,@PathVariable Long offerId){
        return convertToResource(offerService.deactivateOffer(employerId, offerId));
    }
    @Operation(summary = "delete an offer", description = "delete an offer",
            tags = "Employer-Offers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should deactivate an offer",
            content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/employers/{employerId}/offers/{offerId}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long employerId, @PathVariable Long offerId){
        return offerService.deleteOffer(offerId, employerId);
    }

    private Offer convertToEntity(SaveOfferResource resource){
        return new Offer(null,
                resource.getTitle(),
                resource.getDescription(),
                resource.getPaymentAmount(),
                resource.getStartDate(),
                resource.getEndDate(),
                resource.getMonthDuration()
                );
    }

    private OfferResource convertToResource(Offer entity){
        return mapper.map(entity, OfferResource.class);
    }

}
