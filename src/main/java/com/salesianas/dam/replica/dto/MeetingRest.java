package com.salesianas.dam.replica.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesianas.dam.replica.persistence.entity.FinalProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("hour")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hour;
    @JsonProperty("duration")
    private Double duration;

    @JsonProperty("progress")
    private String progress;

    @JsonProperty("finalProject")
    private FinalProjectEntity finalProject;
}
