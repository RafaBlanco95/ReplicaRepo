package com.salesianas.dam.replica.persistence.repository;

import com.salesianas.dam.replica.persistence.entity.FinalProjectEntity;
import com.salesianas.dam.replica.persistence.entity.InternshipEntity;
import com.salesianas.dam.replica.persistence.entity.MeetingEntity;
import com.salesianas.dam.replica.persistence.entity.WorkdayEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends PagingAndSortingRepository<MeetingEntity, Long> {

    @Override
    Page<MeetingEntity> findAll(Pageable page);

    Page<MeetingEntity> findByFinalProject(FinalProjectEntity finalProjectEntity, Pageable pageable);
}
