package com.freelanceworld.hiring.resource.Offer;
import com.freelanceworld.hiring.domain.model.Specialty;
import lombok.Data;

import java.util.Date;
@Data
public class OfferResource {
    private Long id;
    private String title;
    private String description;
    private float paymentAmount;
    private Date startDate;
    private Date endDate;
    private int monthDuration;
    private boolean isActive;
    private Specialty specialty;
}
