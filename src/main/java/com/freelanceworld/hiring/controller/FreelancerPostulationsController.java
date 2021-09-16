package com.freelanceworld.hiring.controller;

import com.freelanceworld.hiring.domain.model.Postulation;
import com.freelanceworld.hiring.domain.service.PostulationService;
import com.freelanceworld.hiring.resource.postulation.PostulationResource;
import com.freelanceworld.hiring.resource.postulation.SavePostulationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/freelancers")
public class FreelancerPostulationsController {
    @Autowired
    private PostulationService postulationService;
    @Autowired
    private ModelMapper mapper;
    @Operation(summary = "Get Postulations", description = "Get All Postulations by freelancerId",
            tags = "Freelancer-Postulations")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Postulations by freelancerId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/{freelancerId}/postulations")
    public List<PostulationResource> getAllOffersByFreelancerId(@PathVariable Long freelancerId){
        List<Postulation> postulations = postulationService.getAllPostulationsByFreelancerId(freelancerId);
        return postulations.stream().map(postulation -> (new PostulationResource(
                postulation.getId(),
                postulation.getDesiredPayment(),
                postulation.getDescription(),
                postulation.getState(),
                postulation.getFreelancer().getId(),
                postulation.getFreelancer().getFirstname(),
                postulation.getFreelancer().getLastname(),
                postulation.getOffer().getTitle(),
                postulation.getOffer().getDescription(),
                postulation.getOffer().getPaymentAmount()
        ))).collect(Collectors.toList());
    }

    @Operation(summary = "Create Postulation", description = "Create Postulation",
            tags = "Freelancer-Postulations")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should create a Postulations",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("/{freelancerId}/offers/{offerId}/postulations")
    public PostulationResource createPostulation(@PathVariable Long freelancerId, @PathVariable Long offerId, @Valid @RequestBody SavePostulationResource resource){
        Postulation postulation = postulationService.createPostulation(offerId, freelancerId, convertToEntity(resource));
        return new PostulationResource(
                postulation.getId(),
                postulation.getDesiredPayment(),
                postulation.getDescription(),
                postulation.getState(),
                postulation.getFreelancer().getId(),
                postulation.getFreelancer().getFirstname(),
                postulation.getFreelancer().getLastname(),
                postulation.getOffer().getTitle(),
                postulation.getOffer().getDescription(),
                postulation.getOffer().getPaymentAmount()
        );
    }


    private Postulation convertToEntity(SavePostulationResource resource){
        return mapper.map(resource, Postulation.class);
    }

    private PostulationResource convertToResource(Postulation entity){
        return mapper.map(entity, PostulationResource.class);
    }

}
