package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    StudentRest getStudent(Long id) throws ReplicaException;

    CustomPagedResourceDTO<StudentRest> listStudents(Pageable pageable) throws ReplicaException;

    StudentRest modifyStudent(StudentRest student, Long id) throws ReplicaException;

    void deleteStudent(Long id) throws ReplicaException;

    StudentRest createStudent(StudentRest studentRest) throws ReplicaException;

}
