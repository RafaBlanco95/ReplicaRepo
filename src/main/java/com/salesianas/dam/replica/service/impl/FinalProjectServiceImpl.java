package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.*;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;
import com.salesianas.dam.replica.mapper.EmployeeMapper;
import com.salesianas.dam.replica.mapper.FinalProjectMapper;
import com.salesianas.dam.replica.mapper.MeetingMapper;
import com.salesianas.dam.replica.persistence.entity.*;
import com.salesianas.dam.replica.persistence.repository.EmployeeRepository;
import com.salesianas.dam.replica.persistence.repository.FinalProjectRepository;
import com.salesianas.dam.replica.persistence.repository.MeetingRepository;
import com.salesianas.dam.replica.service.FinalProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalProjectServiceImpl implements FinalProjectService {

    @Autowired
    private FinalProjectRepository finalProjectRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private FinalProjectMapper finalProjectMapper;

    @Autowired
    private MeetingMapper meetingMapper;

    @Autowired
    CustomPagedResourceAssembler<FinalProjectRest> customPagedResourceAssembler;

    @Autowired
    CustomPagedResourceAssembler<MeetingRest> customPagedResourceAssemblerMeeting;
    @Override
    @Transactional
    public FinalProjectRest getFinalProject(Long id) throws ReplicaException {
        return finalProjectRepository.findById(id)
                .map(finalProject -> finalProjectMapper.finalProjectEntityToFinalProjectRest(finalProject)).orElseThrow( ()->new ReplicaNotFoundException(String.format("FinalProject with ID: [%s] not found.", id), "404"));

    }

    @Override
    public FinalProjectRest getFinalProjectByStudentUsername(String username) throws ReplicaException {
        return finalProjectRepository.findByStudentUsername(username)
                .map(finalProject -> finalProjectMapper.finalProjectEntityToFinalProjectRest(finalProject)).orElseThrow( ()->new ReplicaNotFoundException(String.format("FinalProject with Student Username: [%s] not found.", username), "404"));

    }

    @Override
    public CustomPagedResourceDTO<FinalProjectRest> listFinalProject(Pageable pageable) throws ReplicaException {
        Page<FinalProjectEntity> finalProjectPage = finalProjectRepository.findAll(pageable);
        Page<FinalProjectRest> finalProjectRestPage = finalProjectPage.map(finalProjectMapper::finalProjectEntityToFinalProjectRest);
        return  customPagedResourceAssembler.toModel(finalProjectRestPage);
    }

    @Override
    @Transactional
    public CustomPagedResourceDTO<MeetingRest> listFinalProjectMeetings(Long id, Pageable pageable) throws ReplicaException {
        FinalProjectEntity finalProjectEntity= finalProjectRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("Final Project with ID: [%s] not found.", id), "404"));
        Page<MeetingEntity> meetingPage= meetingRepository.findByFinalProject(finalProjectEntity,pageable);
        Page<MeetingRest> meetingRestPage= meetingPage.map(meetingMapper::meetingEntityToMeetingRest);
        return customPagedResourceAssemblerMeeting.toModel(meetingRestPage);
    }

    @Override
    public FinalProjectRest modifyFinalProject(FinalProjectRest finalProjectRest, Long id) throws ReplicaException {
        return finalProjectMapper.finalProjectEntityToFinalProjectRest(finalProjectRepository.findById(id).map(finalProjectSaved -> {
                    finalProjectSaved = finalProjectMapper.finalProjectRestToFinalProjectEntity(finalProjectRest);
                    finalProjectSaved.setId(id);
                    return finalProjectRepository.save(finalProjectSaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("FinalProject with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public void deleteFinalProject(Long id) throws ReplicaException {
        FinalProjectEntity finalProjectEntity= finalProjectRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("FinalProject with ID: [%s] not found.", id), "404"));
        finalProjectRepository.delete(finalProjectEntity);
    }

    @Override
    public FinalProjectRest createFinalProject(FinalProjectRest finalProjectRest) throws ReplicaException {
        return finalProjectMapper.finalProjectEntityToFinalProjectRest(finalProjectRepository.save(finalProjectMapper.finalProjectRestToFinalProjectEntity(finalProjectRest)));
    }
}
