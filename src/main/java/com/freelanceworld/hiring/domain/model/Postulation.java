package com.freelanceworld.hiring.domain.model;

import com.freelanceworld.accounts.domain.model.Freelancer;
import com.freelanceworld.common.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "postulations")
@Data
public class Postulation extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float desiredPayment;
    private String description;

    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false, updatable = false)
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false, updatable = false)
    private Offer offer;

    private PostState state;

    public enum PostState{
        ACCEPTED,
        REJECTED,
        UNANSWERED
    }
}
