package com.freelanceworld.hiring.controller;
import com.freelanceworld.hiring.domain.model.Meeting;
import com.freelanceworld.hiring.domain.service.MeetingService;
import com.freelanceworld.hiring.resource.Meeting.MeetingResource;
import com.freelanceworld.hiring.resource.Meeting.SaveMeetingResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
public class OfferMeetingsController {
    @Autowired
    private MeetingService meetingService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Meetings", description = "Get All Meetings by offerId",
            tags = "Offer-Meetings")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Meetings by offerId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/{offerId}/meetings")
    public List<MeetingResource> getAllMeetings(@PathVariable Long offerId){
        List<Meeting> meetings = meetingService.getAllMeetingsByOfferId(offerId);
        return meetings.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Operation(summary = "Get Meeting By offerId and id", description = "Get Meeting By offerId and id",
            tags = "Offer-Meetings")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve a Meeting by offerId and id",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/{offerId}/meetings/{meetingId}")
    public MeetingResource getMeetingByOfferIdAndId(@PathVariable Long offerId, @PathVariable Long meetingId){
        Meeting meeting = meetingService.getMeetingByIdAndOfferId(offerId,meetingId);
        return convertToResource(meeting);
    }


    @Operation(summary = "Create Meeting", description = "Create Meeting",
            tags = "Offer-Meetings")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should create a Meeting ",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("/{offerId}/meetings/{postulationId}")
    public MeetingResource createMeeting(@PathVariable Long offerId,@PathVariable Long postulationId, @RequestBody SaveMeetingResource resource){
        Meeting meeting = meetingService.createMeeting(offerId,postulationId,convertToEntity(resource));
        return convertToResource(meeting);
    }

    @Operation(summary = "Put Meeting", description = "Put Meeting",
            tags = "Offer-Meetings")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should update a Meeting",
            content = @Content(mediaType = "application/json"))})
    @PutMapping("/{offerId}/meetings/{meetingId}")
    public MeetingResource updateMeeting(@PathVariable Long offerId,@PathVariable Long meetingId, @RequestBody SaveMeetingResource resource){
        Meeting meeting = meetingService.updateMeeting(offerId,meetingId,convertToEntity(resource));
        return convertToResource(meeting);
    }

    @Operation(summary = "Delete Meeting", description = "Delete Meeting",
            tags = "Offer-Meetings")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should delete a Meeting",
            content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/{offerId}/meetings/{meetingId}")
    public ResponseEntity<?> deleteMeeting(@PathVariable Long offerId, @PathVariable Long meetingId, @RequestBody SaveMeetingResource resource){
        return meetingService.deleteMeeting(offerId,meetingId);
    }

    private Meeting convertToEntity(SaveMeetingResource resource){
        return mapper.map(resource, Meeting.class);
    }

    private MeetingResource convertToResource(Meeting entity){
        return mapper.map(entity, MeetingResource.class);
    }


}
