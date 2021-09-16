package com.freelanceworld.hiring.domain.service;

import com.freelanceworld.hiring.domain.model.Meeting;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MeetingService {
    List<Meeting> getAllMeetingsByOfferId(Long offerId);
    List<Meeting> getAllMeetingsByPostulationId(Long offerId);
    Meeting getMeetingByIdAndOfferId(Long meetingId, Long id);
    Meeting createMeeting(Long offerId, Long postulationId, Meeting meeting);
    Meeting updateMeeting(Long offerId, Long meetingId, Meeting meetingDetails);
    ResponseEntity<?> deleteMeeting(Long offerId, Long meetingId);
}
