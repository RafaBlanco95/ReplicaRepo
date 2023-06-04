package com.salesianas.dam.replica.controller.impl;

import com.salesianas.dam.replica.controller.MeetingControllerRest;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.MeetingRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.response.ReplicaResponse;
import com.salesianas.dam.replica.response.ReplicaResponseStatus;
import com.salesianas.dam.replica.service.impl.MeetingServiceImpl;
import com.salesianas.dam.replica.service.impl.WorkdayServiceImpl;
import com.salesianas.dam.replica.utils.constant.RestConstantsUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Meeting", description = "Meeting rest")
@RequestMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_MEETINGS)
public class MeetingControllerRestImpl implements MeetingControllerRest {

    @Autowired
    private MeetingServiceImpl meetingService;

    @Override
    @GetMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<MeetingRest>> meetingDetails(@PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Meeting successfully recovered")
                .data(meetingService.getMeeting(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping(value = RestConstantsUtils.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<MeetingRest>> modifyMeeting(@RequestBody MeetingRest meeting,@PathVariable Long id) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Meeting successfully updated")
                .data(meetingService.modifyMeeting(meeting, id))
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @Override
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ID)
    public ResponseEntity deleteMeeting(@PathVariable Long id) throws ReplicaException {
       meetingService.deleteMeeting(id);

        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Meeting successfully deleted")
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ReplicaResponse<MeetingRest>> createMeeting(@RequestBody MeetingRest meetingRest) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Meeting successfully created")
                .data(meetingService.createMeeting(meetingRest))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplicaResponse<CustomPagedResourceDTO<MeetingRest>>> listMeetings(@Parameter(hidden=true) Pageable pageable) throws ReplicaException {
        ReplicaResponse response = ReplicaResponse.builder()
                .status(ReplicaResponseStatus.OK)
                .message("Meetings successfully recovered")
                .data(meetingService.listMeetings(pageable))
                .build();

        return ResponseEntity.ok(response);
    }
}
