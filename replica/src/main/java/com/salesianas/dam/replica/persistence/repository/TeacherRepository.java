package com.salesianas.dam.replica.persistence.repository;


import com.salesianas.dam.replica.persistence.entity.TeacherEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends PagingAndSortingRepository<TeacherEntity, Long> {
}
