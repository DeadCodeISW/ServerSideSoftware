package com.freelanceworld.accounts.resource.freelancer;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;
@Data
public class SaveFreelancerResource {
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
    private Date birthDate;
    @NotNull
    @NotBlank
    @Size(min = 9, max = 9)
    private String phone;
    @Size(max = 250)
    private String description;
    @Size(min=3,max = 20)
    private String profession;

}
