package com.salesianas.dam.replica.mapper;

import com.salesianas.dam.replica.dto.InternshipRest;
import com.salesianas.dam.replica.persistence.entity.InternshipEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InternshipMapper {

    InternshipRest internshipEntityToInternshipRest(InternshipEntity internshipEntity);

    List<InternshipRest> internshipEntityListToInternshipRestList(List<InternshipEntity> internshipEntityList );
    InternshipEntity internshipRestToInternshipEntity(InternshipRest internshipRest);
}
