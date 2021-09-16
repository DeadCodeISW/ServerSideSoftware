package com.freelanceworld.hiring.resource.Meeting;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveMeetingResource {
    @NotNull
    @NotBlank
    @Lob
    private String description;
    @NotNull
    private Date meetingDate;
}
