package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeRest getEmployee(Long id) throws ReplicaException;

    CustomPagedResourceDTO<EmployeeRest> listEmployees(Pageable pageable) throws ReplicaException;

    EmployeeRest modifyEmployee(EmployeeRest employeeRest, Long id) throws ReplicaException;

    void deleteEmployee(Long id) throws ReplicaException;

    EmployeeRest createEmployee(EmployeeRest employeeRest) throws ReplicaException;
}
