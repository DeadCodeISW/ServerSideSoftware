package com.freelanceworld.accounts.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "employer_id")
@Table(name = "employers")
@Data
public class Employer extends Profile {

    @NotBlank
    @NotNull
    private String firstname;
    @NotBlank
    @NotNull
    private String lastname;
    @NotBlank
    @NotNull
    private String personalPhone;
    private String description;
    private String companyPhone;
    private String companyName;
    private String companyWeb;
    private String contactCompanyEmail;
}
