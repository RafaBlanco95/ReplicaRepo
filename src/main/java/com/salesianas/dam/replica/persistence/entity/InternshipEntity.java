package com.salesianas.dam.replica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesianas.dam.replica.dto.InternshipType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    @JsonIgnore
    @ManyToOne
    private EmployeeEntity employee;

    @Column(name = "startingDate")
    private LocalDate startingDate;

    @Column(name = "endingDate")
    private LocalDate endingDate;

    @Column(name = "type")
    private String type;

    @Column(name = "totalHours")
    private Integer totalHours;

    @Column(name= "enterprise")
    private String enterprise;

    @JsonIgnore
    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WorkdayEntity> workdays;

}
