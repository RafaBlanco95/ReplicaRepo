package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceAssembler;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;
import com.salesianas.dam.replica.dto.MeetingRest;
import com.salesianas.dam.replica.dto.WorkdayRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;
import com.salesianas.dam.replica.mapper.FinalProjectMapper;
import com.salesianas.dam.replica.mapper.MeetingMapper;
import com.salesianas.dam.replica.mapper.WorkdayMapper;
import com.salesianas.dam.replica.persistence.entity.FinalProjectEntity;
import com.salesianas.dam.replica.persistence.entity.MeetingEntity;
import com.salesianas.dam.replica.persistence.entity.WorkdayEntity;
import com.salesianas.dam.replica.persistence.repository.FinalProjectRepository;
import com.salesianas.dam.replica.persistence.repository.MeetingRepository;
import com.salesianas.dam.replica.persistence.repository.WorkdayRepository;
import com.salesianas.dam.replica.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private FinalProjectRepository finalProjectRepository;

    @Autowired
    private MeetingMapper meetingMapper;

    @Autowired
    private FinalProjectMapper finalProjectMapper;

    @Autowired
    CustomPagedResourceAssembler<MeetingRest> customPagedResourceAssembler;


    @Override
    public MeetingRest getMeeting(Long id) throws ReplicaException {
        return meetingRepository.findById(id)
                .map(meeting -> meetingMapper.meetingEntityToMeetingRest(meeting)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Meeting with ID: [%s] not found.", id), "404"));

    }

    @Override
    public CustomPagedResourceDTO<MeetingRest> listMeetings(Pageable pageable) throws ReplicaException {
        Page<MeetingEntity> meetingPage = meetingRepository.findAll(pageable);
        Page<MeetingRest> meetingRestPage = meetingPage.map(meetingMapper::meetingEntityToMeetingRest);
        return  customPagedResourceAssembler.toModel(meetingRestPage);
    }

    @Override
    public MeetingRest modifyMeeting(MeetingRest meetingRest, Long id) throws ReplicaException {
        return meetingMapper.meetingEntityToMeetingRest(meetingRepository.findById(id).map(meetingSaved -> {
                    meetingSaved = meetingMapper.meetingRestToMeetingEntity(meetingRest);
                    meetingSaved.setId(id);
                    return meetingRepository.save(meetingSaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Meeting with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public void deleteMeeting(Long id) throws ReplicaException {
        MeetingEntity meetingEntity= meetingRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("Meeting with ID: [%s] not found.", id), "404"));
        meetingRepository.delete(meetingEntity);
    }

    @Override
    public MeetingRest createMeeting(MeetingRest meetingRest) throws ReplicaException {
        return meetingMapper.meetingEntityToMeetingRest(meetingRepository.save(meetingMapper.meetingRestToMeetingEntity(meetingRest)));
    }

    @Override
    public MeetingRest createMeetingByFinalProject(MeetingRest meetingRest, Long id) throws ReplicaException {
        FinalProjectEntity finalProjectEntity= finalProjectRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("Final Project with ID: [%s] not found.", id), "404"));
        meetingRest.setFinalProject(finalProjectEntity);
        return meetingMapper.meetingEntityToMeetingRest(meetingRepository.save(meetingMapper.meetingRestToMeetingEntity(meetingRest)));

    }
}
