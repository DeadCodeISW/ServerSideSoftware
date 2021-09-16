package com.freelanceworld.accounts.resource;

import lombok.Data;

@Data
public abstract class ProfileResource {
    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String webPage;
    private String facebookLink;
    private String instagramLink;
    private String twitterLink;
    private String imageUrl;
}
