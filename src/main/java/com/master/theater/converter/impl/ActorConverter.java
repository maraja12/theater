package com.master.theater.converter.impl;

import com.master.theater.converter.DtoEntityConverter;
import com.master.theater.domain.Actor;
import com.master.theater.dto.ActorDto;
import org.springframework.stereotype.Component;

@Component
public class ActorConverter implements DtoEntityConverter<ActorDto, Actor> {
    @Override
    public ActorDto toDto(Actor entity) {
        return ActorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }

    @Override
    public Actor toEntity(ActorDto dto) {
        return Actor.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .build();
    }
}
