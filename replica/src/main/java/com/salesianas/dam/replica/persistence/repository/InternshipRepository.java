package com.salesianas.dam.replica.persistence.repository;


import com.salesianas.dam.replica.persistence.entity.InternshipEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipRepository extends PagingAndSortingRepository<InternshipEntity, Long> {
}
