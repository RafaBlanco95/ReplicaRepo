package com.salesianas.dam.replica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesianas.dam.replica.persistence.entity.InternshipEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkdayRest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("hours")
    private Double hours;
    @JsonProperty("description")
    private String description;
    @JsonProperty("isValidated")
    private Boolean isValidated;

    @JsonProperty("internship")
    private InternshipEntity internship;
}
