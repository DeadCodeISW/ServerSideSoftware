package com.freelanceworld.accounts.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "freelancer_id")
@Table(name = "freelancers")
@Data
public class Freelancer extends Profile{

    @NotBlank
    @NotNull
    private String firstname;
    @NotBlank
    @NotNull
    private String lastname;
    private Date birthDate;
    @NotBlank
    @NotNull
    private String phone;
    private String description;
    private String profession;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "district_id", nullable = false)
//    private District district;
}
