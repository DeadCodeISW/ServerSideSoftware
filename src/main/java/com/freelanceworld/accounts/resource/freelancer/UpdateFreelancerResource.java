package com.freelanceworld.accounts.resource.freelancer;
import lombok.Data;
import javax.validation.constraints.Size;
@Data
public class UpdateFreelancerResource {

    @Size(min = 9, max = 9)
    private String phone;
    @Size(max = 250)
    private String description;
    @Size(min=3,max = 40)
    private String profession;
    private String webPage;
    private String facebookLink;
    private String instagramLink;
    private String twitterLink;
    private String imageUrl;
}
