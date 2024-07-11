package com.master.theater.service;

import com.master.theater.dto.ActorDto;
import com.master.theater.exception.EntityNotFoundException;

import java.util.List;

public interface ActorService {

    List<ActorDto> getAll();
    ActorDto save(ActorDto actorDto);
    ActorDto update(ActorDto actorDto) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
    ActorDto findById(Long id) throws EntityNotFoundException;
}
