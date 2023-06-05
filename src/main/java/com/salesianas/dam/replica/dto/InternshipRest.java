package com.salesianas.dam.replica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesianas.dam.replica.persistence.entity.EmployeeEntity;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import com.salesianas.dam.replica.persistence.entity.WorkdayEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternshipRest {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("student")
    private StudentEntity student;

    @JsonProperty("employee")
    private EmployeeEntity employee;

    @JsonProperty("startingDate")
    private LocalDate startingDate;

    @JsonProperty("endingDate")
    private LocalDate endingDate;

    @JsonProperty("type")
    private String type;

    @JsonProperty("totalHours")
    private Integer totalHours;

    @JsonProperty("enterprise")
    private String enterprise;
    @JsonProperty("workdays")
    private List<WorkdayEntity> workdays;
}
