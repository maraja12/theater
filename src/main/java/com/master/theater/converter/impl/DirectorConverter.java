package com.master.theater.converter.impl;

import com.master.theater.converter.DtoEntityConverter;
import com.master.theater.domain.Director;
import com.master.theater.dto.DirectorDto;
import org.springframework.stereotype.Component;

@Component
public class DirectorConverter implements DtoEntityConverter<DirectorDto, Director> {
    @Override
    public DirectorDto toDto(Director entity) {
        return DirectorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }

    @Override
    public Director toEntity(DirectorDto dto) {
        return Director.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .build();
    }
}
