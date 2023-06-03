package com.salesianas.dam.replica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workday")
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkdayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;
    @Column(name = "hours")
    private Double hours;
    @Column(name = "description")
    private String description;
    @Column(name = "isValidated")
    private Boolean isValidated;

    @JsonIgnore
    @ManyToOne
    private InternshipEntity internship;
}
