package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.dto.WorkdayRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import org.springframework.data.domain.Pageable;

public interface WorkdayService {


    WorkdayRest getWorkday(Long id) throws ReplicaException;

    CustomPagedResourceDTO<WorkdayRest> listWorkdays(Pageable pageable) throws ReplicaException;

    WorkdayRest modifyWorkday(WorkdayRest workdayRest, Long id) throws ReplicaException;

    WorkdayRest validateWorkday(Long id) throws ReplicaException;

    void deleteWorkday(Long id) throws ReplicaException;

    WorkdayRest createWorkday(WorkdayRest workdayRest) throws ReplicaException;

    WorkdayRest createWorkdayByInternship(WorkdayRest workdayRest, Long id) throws ReplicaException;
}
