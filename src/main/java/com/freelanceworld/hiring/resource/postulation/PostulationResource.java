package com.freelanceworld.hiring.resource.postulation;

import com.freelanceworld.hiring.domain.model.Postulation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostulationResource {
    private Long id;
    private float desiredPayment;
    private String description;
    private Postulation.PostState state;
    private Long freelancerId;
    private String firstname;
    private String lastname;
    private String title;
    private String offerDescription;
    private float paymentAmount;
}
