package com.freelanceworld.accounts.domain.model.background;

import com.freelanceworld.accounts.domain.model.Freelancer;
import com.freelanceworld.common.SkillLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "skills")
@Data
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "freelancer_id", nullable = false)
    @JsonIgnore
    private Freelancer freelancer;
    @NotNull
    private String name;
    @Lob
    private String description;
    @Enumerated(value = EnumType.STRING)
    private SkillLevel level;

}
