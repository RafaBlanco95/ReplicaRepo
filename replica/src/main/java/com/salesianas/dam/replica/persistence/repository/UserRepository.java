package com.salesianas.dam.replica.persistence.repository;

import com.salesianas.dam.replica.persistence.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
}
