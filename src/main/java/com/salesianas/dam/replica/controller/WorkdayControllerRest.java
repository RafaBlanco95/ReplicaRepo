package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.dto.WorkdayRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface WorkdayControllerRest {
    ResponseEntity<ReplicaResponse<WorkdayRest>> workdayDetails(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<WorkdayRest>>  modifyWorkday(WorkdayRest workday, Long id) throws ReplicaException;

    ResponseEntity deleteWorkday(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<WorkdayRest>>  createWorkday(WorkdayRest workdayRest) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<WorkdayRest>>> listWorkdays(
            Pageable pageable
    ) throws ReplicaException;


}
