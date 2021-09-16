package com.freelanceworld.hiring.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "specialities")
@Data
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "specialty", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @JsonIgnore
    private List<Offer> offers;
}
