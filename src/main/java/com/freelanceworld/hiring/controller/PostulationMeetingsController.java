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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/postulations")
public class PostulationMeetingsController {

    @Autowired
    private MeetingService meetingService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Meetings", description = "Get All Meetings by postulationId",
            tags = "Postulation-Meetings")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Meetings by postulationId",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/{postulationId}/meetings")
    public List<MeetingResource> getAllMeetings(@PathVariable Long postulationId){
        List<Meeting> meetings = meetingService.getAllMeetingsByPostulationId(postulationId);
        return meetings.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    private Meeting convertToEntity(SaveMeetingResource resource){
        return mapper.map(resource, Meeting.class);
    }

    private MeetingResource convertToResource(Meeting entity){
        return mapper.map(entity, MeetingResource.class);
    }
}
