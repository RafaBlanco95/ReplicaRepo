package com.salesianas.dam.replica.persistence.repository;


import com.salesianas.dam.replica.persistence.entity.EmployeeEntity;
import com.salesianas.dam.replica.persistence.entity.InternshipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipRepository extends PagingAndSortingRepository<InternshipEntity, Long> {
    @Override
    Page<InternshipEntity> findAll(Pageable page);

    List<InternshipEntity> findByStudentUsername(String username);

    Page<InternshipEntity> findByEmployee(Pageable page, EmployeeEntity employeeEntity);
}
