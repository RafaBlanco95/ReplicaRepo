package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.dto.WorkdayRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InternshipControllerRest {

    ResponseEntity<ReplicaResponse<InternshipRest>> internshipDetails(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<InternshipRest>>  modifyInternship(InternshipRest internship, Long id) throws ReplicaException;

    ResponseEntity deleteInternship(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<InternshipRest>>  createInternship(InternshipRest internshipRest) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<InternshipRest>>> listInternships(
            Pageable pageable
    ) throws ReplicaException;

    ResponseEntity<ReplicaResponse<List<InternshipRest>>> listInternshipsByUsername(
            String username
    ) throws ReplicaException;

    ResponseEntity<ReplicaResponse<List<InternshipRest>>> listInternshipsByEmployee(
            String username, Pageable pageable
    ) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<WorkdayRest>>> listInternshipWorkdays(
            Long id,
            Pageable pageable
    ) throws ReplicaException;
}
