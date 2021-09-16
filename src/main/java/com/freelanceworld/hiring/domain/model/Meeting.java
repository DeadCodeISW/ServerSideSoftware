package com.freelanceworld.hiring.domain.model;

import com.freelanceworld.common.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meetings")
@Data
public class Meeting extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "postulation_id", nullable = false, updatable = false)
    private Postulation postulation;
    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false, updatable = false)
    private Offer offer;
    private Date meetingDate;
}
