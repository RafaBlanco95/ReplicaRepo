package com.salesianas.dam.replica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "meeting")
@Data
@EqualsAndHashCode(callSuper = false)
public class MeetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "hour")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hour;
    @Column(name = "duration")
    private Double duration;

    @Column(name = "progress")
    private String progress;

    @JsonIgnore
    @ManyToOne
    private FinalProjectEntity finalProject;
}
