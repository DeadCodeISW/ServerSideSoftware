package com.freelanceworld.hiring.resource.Offer;
import lombok.Data;
import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.util.Date;
@Data
public class SaveOfferResource {
    @NotNull
    @NotBlank
    @Size(min = 2,max = 500)
    private String title;
    @NotNull
    @NotBlank
    @Lob
    private String description;
    @NotNull
    @PositiveOrZero
    private float paymentAmount;
    @NotNull
    @PositiveOrZero
    private int monthDuration;
    private Date startDate;
    private Date endDate;
    @PositiveOrZero
    private Long specialtyId;
}
