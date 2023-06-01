package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.FinalProjectRest;
import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.payload.request.EditRequest;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    StudentRest getStudent(Long id) throws ReplicaException;

    StudentRest getStudentByUsername(String username) throws ReplicaException;

    CustomPagedResourceDTO<StudentRest> listStudents(Pageable pageable) throws ReplicaException;

    StudentRest modifyStudent(StudentRest student, Long id) throws ReplicaException;

    StudentRest editStudent(EditRequest student, Long id) throws ReplicaException;

    StudentRest addInternshipToStudent(InternshipRest internship, Long id) throws ReplicaException;

    StudentRest addFinalProjectToStudent(FinalProjectRest finalProject, Long id) throws ReplicaException;

    StudentRest associateTeacherToStudent(String username, Long id) throws ReplicaException;

    StudentRest associateEmployeeToStudentInternship(String username, Long id) throws ReplicaException;

    void deleteStudent(Long id) throws ReplicaException;

    StudentRest createStudent(StudentRest studentRest) throws ReplicaException;

}
