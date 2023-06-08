package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.FinalProjectRest;
import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.dto.WorkdayRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InternshipService {

    InternshipRest getInternship(Long id) throws ReplicaException;

    CustomPagedResourceDTO<InternshipRest> listInternships(Pageable pageable) throws ReplicaException;

    CustomPagedResourceDTO<WorkdayRest> listInternshipWorkdays(Long id, Pageable pageable) throws ReplicaException;

    List<InternshipRest> listInternshipByStudentUsername(String username) throws ReplicaException;

    CustomPagedResourceDTO<InternshipRest> listInternshipsByEmployee(Pageable pageable, String username) throws ReplicaException;

    InternshipRest modifyInternship(InternshipRest internshipRest, Long id) throws ReplicaException;

    void deleteInternship(Long id) throws ReplicaException;

    InternshipRest createInternship(InternshipRest internshipRest) throws ReplicaException;
}
