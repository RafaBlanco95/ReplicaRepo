package com.salesianas.dam.replica.controller;

import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.MeetingRest;
import com.salesianas.dam.replica.dto.WorkdayRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MeetingControllerRest {
    ResponseEntity<ReplicaResponse<MeetingRest>> meetingDetails(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<MeetingRest>>  modifyMeeting(MeetingRest meeting, Long id) throws ReplicaException;

    ResponseEntity deleteMeeting(Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<MeetingRest>>  createMeeting(MeetingRest meetingRest) throws ReplicaException;

    ResponseEntity<ReplicaResponse<MeetingRest>>  createMeetingByFinalProject(MeetingRest meetingRest, Long id) throws ReplicaException;

    ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<MeetingRest>>> listMeetings(
            Pageable pageable
    ) throws ReplicaException;

}
