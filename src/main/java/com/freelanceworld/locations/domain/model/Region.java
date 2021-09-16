package com.freelanceworld.locations.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@PrimaryKeyJoinColumn(name = "region_id")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
