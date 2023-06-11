package com.salesianas.dam.replica.persistence.repository;


import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import com.salesianas.dam.replica.persistence.entity.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {
    @Override
    Page<StudentEntity> findAll(Pageable page);

    Optional<StudentEntity> findByUsername(String username);

    Page<StudentEntity> findByTeacher(TeacherEntity teacher, Pageable page);

    @Override
    @Query(value="DELETE FROM student WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
