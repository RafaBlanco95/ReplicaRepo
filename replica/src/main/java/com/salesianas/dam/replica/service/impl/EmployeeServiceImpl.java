package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.EmployeeRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.service.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public EmployeeRest getEmployee(Long id) throws ReplicaException {
        return null;
    }

    @Override
    public CustomPagedResourceDTO<EmployeeRest> listEmployees(Pageable pageable) throws ReplicaException {
        return null;
    }

    @Override
    public EmployeeRest modifyEmployee(EmployeeRest employeeRest, Long id) throws ReplicaException {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) throws ReplicaException {

    }

    @Override
    public EmployeeRest createEmployee(EmployeeRest employeeRest) throws ReplicaException {
        return null;
    }
}
