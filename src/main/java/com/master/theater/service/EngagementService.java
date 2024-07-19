package com.master.theater.service;

import com.master.theater.dto.ActorDto;
import com.master.theater.dto.EngagementDto;
import com.master.theater.exception.EntityNotFoundException;

import java.util.List;

public interface EngagementService {

    List<EngagementDto> getAll();
    EngagementDto save(EngagementDto engagementDto) throws EntityNotFoundException;
    EngagementDto update(EngagementDto engagementDto) throws EntityNotFoundException;
    EngagementDto findByActorIdAndShowId(Long actorId, Long showId) throws EntityNotFoundException;
    List<EngagementDto> findByActorId(Long actorId) throws EntityNotFoundException;
    List<EngagementDto> findByShowId(Long showId) throws EntityNotFoundException;
    List<EngagementDto> findCurrentEngagements();
    List<EngagementDto> findCurrentByActorId(Long actorId) throws EntityNotFoundException;
    List<EngagementDto> findCurrentByShowId(Long showId) throws EntityNotFoundException;

}
