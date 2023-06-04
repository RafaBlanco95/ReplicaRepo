package com.salesianas.dam.replica.service;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.MeetingRest;
import com.salesianas.dam.replica.dto.WorkdayRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import org.springframework.data.domain.Pageable;

public interface MeetingService {

    MeetingRest getMeeting(Long id) throws ReplicaException;

    CustomPagedResourceDTO<MeetingRest> listMeetings(Pageable pageable) throws ReplicaException;

    MeetingRest modifyMeeting(MeetingRest meetingRest, Long id) throws ReplicaException;

    void deleteMeeting(Long id) throws ReplicaException;

    MeetingRest createMeeting(MeetingRest meetingRest) throws ReplicaException;

    MeetingRest createMeetingByFinalProject(MeetingRest meetingRest, Long id) throws ReplicaException;
}
