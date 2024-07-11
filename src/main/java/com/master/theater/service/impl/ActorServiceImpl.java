package com.master.theater.service.impl;

import com.master.theater.converter.impl.ActorConverter;
import com.master.theater.domain.Actor;
import com.master.theater.dto.ActorDto;
import com.master.theater.exception.EntityNotFoundException;
import com.master.theater.repository.ActorRepository;
import com.master.theater.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    private ActorRepository actorRepository;
    private ActorConverter actorConverter;

    public ActorServiceImpl(ActorRepository actorRepository, ActorConverter actorConverter) {
        this.actorRepository = actorRepository;
        this.actorConverter = actorConverter;
    }

    @Override
    public List<ActorDto> getAll() {
        return actorRepository.findAll()
                .stream().map(entity -> actorConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public ActorDto save(ActorDto actorDto) {
        Actor actor = actorConverter.toEntity(actorDto);
        actor = actorRepository.save(actor);
        return actorConverter.toDto(actor);
    }

    @Override
    public ActorDto update(ActorDto actorDto) throws EntityNotFoundException {
        Optional<Actor> presentActor = actorRepository.findById(actorDto.getId());
        if(presentActor.isPresent()){
            Actor actor = presentActor.get();
            actor.setName(actorDto.getName());
            actor.setSurname(actorDto.getSurname());
            actor = actorRepository.save(actor);
            return actorConverter.toDto(actor);
        }
        else{
            throw new EntityNotFoundException("Actor with id = " + actorDto.getId() +
                    " does not exist!");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Optional<Actor> presentActor = actorRepository.findById(id);
        if(presentActor.isPresent()){
            Actor actor = presentActor.get();
            actorRepository.delete(actor);
        }
        else{
            throw new EntityNotFoundException("Actor with id = " + id +
                    " does not exist!");
        }
    }

    @Override
    public ActorDto findById(Long id) throws EntityNotFoundException {
        Optional<Actor> presentActor = actorRepository.findById(id);
        if(presentActor.isPresent()){
            Actor actor = presentActor.get();
            return actorConverter.toDto(actor);
        }
        else{
            throw new EntityNotFoundException("Actor with id = " + id +
                    " does not exist!");
        }
    }

}
