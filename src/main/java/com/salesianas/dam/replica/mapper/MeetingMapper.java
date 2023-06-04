package com.salesianas.dam.replica.mapper;


import com.salesianas.dam.replica.dto.MeetingRest;
import com.salesianas.dam.replica.persistence.entity.MeetingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeetingMapper {

    MeetingEntity meetingRestToMeetingEntity(MeetingRest meetingRest);

    MeetingRest meetingEntityToMeetingRest(MeetingEntity meetingEntity);
}
