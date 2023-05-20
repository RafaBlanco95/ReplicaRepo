package com.salesianas.dam.replica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesianas.dam.replica.dto.InternshipType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "internship")
@Data
@EqualsAndHashCode(callSuper = false)
public class InternshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private StudentEntity student;

    @ManyToOne
    private EmployeeEntity employee;

    @Column(name = "startingDate")
    private LocalTime startingDate;

    @Column(name = "endingDate")
    private LocalTime endingDate;

    @Column(name = "type")
    private InternshipType type;

    @Column(name = "totalHours")
    private Integer totalHours;

}
