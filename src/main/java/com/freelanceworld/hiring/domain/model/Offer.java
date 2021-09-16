package com.freelanceworld.hiring.domain.model;

import com.freelanceworld.accounts.domain.model.Employer;
import com.freelanceworld.common.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "offers")
@Data
public class Offer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false, updatable = false)
    private Employer employer;
    private float paymentAmount;
    private Date startDate;
    private Date endDate;
    private boolean isActive = true;
    private int monthDuration;
    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @JsonIgnore
    private List<Postulation> postulations;

    @ManyToOne
    @JoinColumn(name = "specialty_id", nullable = false, updatable = false)
    private Specialty specialty;


    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @JsonIgnore
    private List<Requirement> requirements;

    public Offer(Long id, String title, String description, float paymentAmount, Date startDate, Date endDate, int monthDuration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.paymentAmount = paymentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthDuration = monthDuration;
    }
    public Offer(){}
}
