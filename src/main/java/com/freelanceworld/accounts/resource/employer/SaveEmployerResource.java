package com.freelanceworld.accounts.resource.employer;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveEmployerResource {

    @NotNull
    @NotBlank
    @Size(min = 5,max = 20)
    private String username;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 6,max = 20)
    private String password;
    @NotNull
    @NotBlank
    @Size(min = 2,max = 20)
    private String firstname;
    @NotNull
    @NotBlank
    @Size(min = 2,max = 20)
    private String lastname;
    private String personalPhone;
    private String description;
    private String companyPhone;
    @Size(min = 2,max = 100)
    private String companyName;
    private String companyWeb;
    private String contactCompanyEmail;
}
