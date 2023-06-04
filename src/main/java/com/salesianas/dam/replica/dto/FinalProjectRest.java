package com.salesianas.dam.replica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesianas.dam.replica.persistence.entity.MeetingEntity;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalProjectRest implements Serializable {


    @JsonProperty("student")
    private StudentEntity student;

    @JsonProperty("title")
    private String title;

    @JsonProperty("expositionDate")
    private LocalDate expositionDate;
    @JsonProperty("meetings")
    private List<MeetingEntity> meetings;
}
