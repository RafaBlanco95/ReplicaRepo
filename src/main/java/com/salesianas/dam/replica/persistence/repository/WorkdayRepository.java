package com.salesianas.dam.replica.persistence.repository;



import com.salesianas.dam.replica.persistence.entity.InternshipEntity;
import com.salesianas.dam.replica.persistence.entity.WorkdayEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkdayRepository extends PagingAndSortingRepository<WorkdayEntity, Long> {

    @Override
    Page<WorkdayEntity> findAll(Pageable page);

    Page<WorkdayEntity> findByInternship(InternshipEntity internshipEntity, Pageable pageable);

    void deleteById(Long id);
}
