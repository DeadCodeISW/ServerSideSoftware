package com.freelanceworld.hiring.resource.postulation;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
@Data
public class SavePostulationResource {
    @PositiveOrZero
    private float desiredPayment;
    @NotNull
    @NotBlank
    @Lob
    private String description;
}
