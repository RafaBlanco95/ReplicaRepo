package com.salesianas.dam.replica.service.impl;

import com.salesianas.dam.replica.dto.CustomPagedResourceAssembler;
import com.salesianas.dam.replica.dto.CustomPagedResourceDTO;

import com.salesianas.dam.replica.dto.WorkdayRest;
import com.salesianas.dam.replica.exception.ReplicaException;
import com.salesianas.dam.replica.exception.ReplicaNotFoundException;

import com.salesianas.dam.replica.mapper.WorkdayMapper;

import com.salesianas.dam.replica.persistence.entity.WorkdayEntity;

import com.salesianas.dam.replica.persistence.repository.WorkdayRepository;
import com.salesianas.dam.replica.service.WorkdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorkdayServiceImpl implements WorkdayService {

    @Autowired
    private WorkdayRepository workdayRepository;

    @Autowired
    private WorkdayMapper workdayMapper;

    @Autowired
    CustomPagedResourceAssembler<WorkdayRest> customPagedResourceAssembler;

    @Override
    public WorkdayRest getWorkday(Long id) throws ReplicaException {
        return workdayRepository.findById(id)
                .map(workday -> workdayMapper.workdayEntityToWorkdayRest(workday)).orElseThrow( ()->new ReplicaNotFoundException(String.format("Workday with ID: [%s] not found.", id), "404"));

    }

    @Override
    public CustomPagedResourceDTO<WorkdayRest> listWorkdays(Pageable pageable) throws ReplicaException {
        Page<WorkdayEntity> workdayPage = workdayRepository.findAll(pageable);
        Page<WorkdayRest> workdayRestPage = workdayPage.map(workdayMapper::workdayEntityToWorkdayRest);
        return  customPagedResourceAssembler.toModel(workdayRestPage);
    }

    @Override
    public WorkdayRest modifyWorkday(WorkdayRest workdayRest, Long id) throws ReplicaException {
        return workdayMapper.workdayEntityToWorkdayRest(workdayRepository.findById(id).map(workdaySaved -> {
                    workdaySaved = workdayMapper.workdayRestToWorkdayEntity(workdayRest);
                    workdaySaved.setId(id);
                    return workdayRepository.save(workdaySaved);
                }).orElseThrow(() -> new ReplicaNotFoundException(String.format("Workday with ID: [%s] not found.", id), "404"))
        );
    }

    @Override
    public void deleteWorkday(Long id) throws ReplicaException {
        WorkdayEntity workdayEntity= workdayRepository.findById(id).orElseThrow( ()->new ReplicaNotFoundException(String.format("Workday with ID: [%s] not found.", id), "404"));
        workdayRepository.delete(workdayEntity);
    }

    @Override
    public WorkdayRest createWorkday(WorkdayRest workdayRest) throws ReplicaException {
        return workdayMapper.workdayEntityToWorkdayRest(workdayRepository.save(workdayMapper.workdayRestToWorkdayEntity(workdayRest)));
    }
}
