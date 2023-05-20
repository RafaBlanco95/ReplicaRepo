package com.salesianas.dam.replica.persistence.repository;

import com.salesianas.dam.replica.persistence.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
    Page<EmployeeEntity> findAll(Pageable page);
}
