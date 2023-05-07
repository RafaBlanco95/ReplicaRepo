package com.salesianas.dam.replica.controller.impl;

import com.salesianas.dam.replica.controller.StudentControllerRest;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.StudentRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import com.salesianas.dam.replica.response.ReplicaResponseStatus;
import com.salesianas.dam.replica.service.impl.StudentServiceImpl;
import com.salesianas.dam.replica.utils.constant.RestConstantsUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Student", description ="Student rest")
@RequestMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_STUDENTS, produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentControllerRestImpl implements StudentControllerRest {

    @Autowired
    private StudentServiceImpl studentService;

    @Override
    @GetMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('administrador')")
    public ResponseEntity<ReplicaResponse<StudentRest>> studentDetails(@PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Student successfully recovered")
                .data(studentService.getStudent(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<StudentRest>> modifyStudent(StudentRest student, Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Student successfully updated")
                .data(studentService.modifyStudent(student, id))
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @Override
    public ResponseEntity deleteStudent(Long id) throws ReplicaException {
        return null;
    }

    @Override
    public ResponseEntity<ReplicaResponse<StudentRest>> createStudent(StudentRest studentRest) throws ReplicaException {
        return null;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<StudentRest>>> listStudents(@Parameter(hidden=true)Pageable pageable) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Students successfully recovered")
                .data(studentService.listStudents(pageable))
                .build();

        return ResponseEntity.ok(response);
    }
}
