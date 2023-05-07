package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.TeacherRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.service.TeacherService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Override
    public TeacherRest getTeacher(Long id) throws ReplicaException {
        return null;
    }

    @Override
    public CustomPagedResourceDTO<TeacherRest> listTeachers(Pageable pageable) throws ReplicaException {
        return null;
    }

    @Override
    public TeacherRest modifyTeacher(TeacherRest teacher, Long id) throws ReplicaException {
        return null;
    }

    @Override
    public void deleteTeacher(Long id) throws ReplicaException {

    }

    @Override
    public TeacherRest createTeacher(TeacherRest teacherRest) throws ReplicaException {
        return null;
    }
}
