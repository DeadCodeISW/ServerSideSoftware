package com.freelanceworld.accounts.domain.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.MappedSuperclass;


@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Profile extends User {

    private String webPage;
    private String facebookLink;
    private String instagramLink;
    private String twitterLink;
    private String imageUrl;

}
