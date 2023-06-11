package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.*;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;
import com.salesianas.dam.replica.mapper.FinalProjectMapper;
import com.salesianas.dam.replica.mapper.InternshipMapper;
import com.salesianas.dam.replica.mapper.StudentMapper;
import com.salesianas.dam.replica.mapper.TeacherMapper;
import com.salesianas.dam.replica.payload.request.EditRequest;
import com.salesianas.dam.replica.persistence.entity.*;
import com.salesianas.dam.replica.persistence.repository.*;
import com.salesianas.dam.replica.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private FinalProjectRepository finalProjectRepository;

    @Autowired
    private InternshipMapper internshipMapper;

    @Autowired
    private FinalProjectMapper finalProjectMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    CustomPagedResourceAssembler<StudentRest> customPagedResourceAssembler;

    @Override
    public StudentRest getStudent(Long id) throws ReplicaException {
        return studentRepository.findById(id)
                .map(student -> studentMapper.studentEntityToStudentRest(student)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"));

    }

    @Override
    public StudentRest getStudentByUsername(String username) throws ReplicaException {
        return studentRepository.findByUsername(username)
                .map(student -> studentMapper.studentEntityToStudentRest(student)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Student with Username: [%s] not found.", username), "404"));

    }

    @Override
    public CustomPagedResourceDTO<StudentRest> listStudents(@Parameter(hidden=true)Pageable pageable) throws ReplicaException {
        Page<StudentEntity> studentPage = studentRepository.findAll(pageable);
        Page<StudentRest> studentRestPage = studentPage.map(studentMapper::studentEntityToStudentRest);
        return  customPagedResourceAssembler.toModel(studentRestPage);

    }

    @Override
    public CustomPagedResourceDTO<StudentRest> listStudentsByTeacher(Pageable pageable, String username) throws ReplicaException {
        TeacherEntity teacherEntity= teacherRepository.findByUsername(username).orElseThrow( ()->new ReplicaNotFoundException(String.format("Teacher with Username: [%s] not found.", username), "404"));


        Page<StudentEntity> studentPage = studentRepository.findByTeacher(teacherEntity, pageable);
        Page<StudentRest> studentRestPage = studentPage.map(studentMapper::studentEntityToStudentRest);
        return  customPagedResourceAssembler.toModel(studentRestPage);
    }

    @Override
    public StudentRest modifyStudent(StudentRest studentRest, Long id) throws ReplicaException {
        return studentMapper.studentEntityToStudentRest(studentRepository.findById(id).map(studentSaved -> {
                    studentSaved.setId(id);
                    studentSaved.setUsername(studentRest.getUsername());
                    studentSaved.setName(studentRest.getName());
                    studentSaved.setLastName(studentRest.getLastName());
                    studentSaved.setLogin_user(studentRest.getLogin_user());

                    return studentRepository.save(studentSaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public StudentRest editStudent(EditRequest student, Long id) throws ReplicaException {
        StudentEntity studentEntity= studentRepository.findById(id).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"));
        studentEntity.setUsername(student.getUsername());
        StudentEntity studentSaved=studentRepository.save(studentEntity);
        return studentMapper.studentEntityToStudentRest(studentSaved);
    }

    @Override
    public StudentRest addInternshipToStudent(InternshipRest internship, Long id) throws ReplicaException {
       StudentEntity studentEntity= studentRepository.findById(id).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"));
       internship.setStudent(studentEntity);
       InternshipEntity internshipEntity= internshipRepository.save(internshipMapper.internshipRestToInternshipEntity(internship));
        return studentMapper.studentEntityToStudentRest(studentRepository.findById(id).map(studentSaved->{
            List<InternshipEntity> internshipEntityList= new ArrayList<>();
            internshipEntityList.add(internshipEntity);
            studentSaved.setInternships(internshipEntityList);
            return studentRepository.save(studentSaved);
        }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404")));
    }

    @Override
    public StudentRest addFinalProjectToStudent(FinalProjectRest finalProject, Long id) throws ReplicaException {
        StudentEntity studentEntity= studentRepository.findById(id).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"));
        finalProject.setStudent(studentEntity);
        FinalProjectEntity finalProjectEntity= finalProjectRepository.save(finalProjectMapper.finalProjectRestToFinalProjectEntity(finalProject));
        return studentMapper.studentEntityToStudentRest(studentRepository.findById(id).map(studentSaved->{
            studentSaved.setFinalProject(finalProjectEntity);
            return studentRepository.save(studentSaved);
        }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404")));
    }

    @Override
    public StudentRest associateTeacherToStudent(String username, Long id) throws ReplicaException {

        StudentEntity studentEntity= studentRepository.findByUsername(username).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with Username: [%s] not found.", username), "404"));
        TeacherEntity teacherEntity= teacherRepository.findById(id).orElseThrow(() -> new ReplicaNotFoundException(String.format("Teacher with ID: [%s] not found.", id), "404"));
        List<StudentEntity> studentEntityList= teacherEntity.getStudents();
        studentEntityList.add(studentEntity);
        teacherEntity.setStudents(studentEntityList);
        teacherRepository.save(teacherEntity);

        return studentMapper.studentEntityToStudentRest(studentRepository.findByUsername(username).map(studentSaved->{
            studentSaved.setTeacher(teacherEntity);
            return studentRepository.save(studentSaved);
        }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with Username: [%s] not found.", username), "404")));
    }

    @Override
    public StudentRest associateEmployeeToStudentInternship(String username, Long id) throws ReplicaException {
        StudentEntity studentEntity= studentRepository.findByUsername(username).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with Username: [%s] not found.", username), "404"));
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new ReplicaNotFoundException(String.format("Employee with ID: [%s] not found.", id), "404"));
        List<InternshipEntity> internshipEntityList= studentEntity.getInternships();
        internshipEntityList.get(0).setEmployee(employeeEntity);
        employeeEntity.setInternships(studentEntity.getInternships());
        employeeRepository.save(employeeEntity);
        return studentMapper.studentEntityToStudentRest(studentRepository.findByUsername(username).map(studentSaved->{
            studentSaved.setInternships(employeeEntity.getInternships());
            return studentRepository.save(studentSaved);
        }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Student with Username: [%s] not found.", username), "404")));
    }

    @Override
    public void deleteStudent(Long id) throws ReplicaException {
        StudentEntity studentEntity= studentRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("Student with ID: [%s] not found.", id), "404"));
        studentEntity.getInternships().stream().map(internshipEntity -> {
            internshipEntity.setStudent(null);
            return internshipRepository.save(internshipEntity);
        }).toList();
        FinalProjectEntity finalProject=studentEntity.getFinalProject();
        finalProject.setStudent(null);
        finalProjectRepository.save(finalProject);
        studentRepository.deleteById(id);
    }

    @Override
    public StudentRest createStudent(StudentRest studentRest) throws ReplicaException {
        return studentMapper.studentEntityToStudentRest(studentRepository.save(studentMapper.studentRestToStudentEntity(studentRest)));
    }
}
