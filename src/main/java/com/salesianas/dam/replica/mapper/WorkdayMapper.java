package com.salesianas.dam.replica.mapper;

import com.salesianas.dam.replica.dto.WorkdayRest;
import com.salesianas.dam.replica.persistence.entity.WorkdayEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkdayMapper {

    WorkdayRest workdayEntityToWorkdayRest(WorkdayEntity workdayEntity);

    WorkdayEntity workdayRestToWorkdayEntity(WorkdayRest workdayRest);
}
