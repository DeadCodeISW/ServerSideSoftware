package com.freelanceworld.hiring.domain.model;

import com.freelanceworld.common.SkillLevel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "requirements")
@Data
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private SkillLevel level;
    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false, updatable = false)
    private Offer offer;
}
