package com.master.theater.converter.impl;

import com.master.theater.converter.DtoEntityConverter;
import com.master.theater.domain.Show;
import com.master.theater.dto.ShowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowConverter implements DtoEntityConverter<ShowDto, Show> {

    @Autowired
    private DirectorConverter directorConverter;
    @Autowired
    private WriterConverter writerConverter;

    @Override
    public ShowDto toDto(Show entity) {
        return ShowDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .duration(entity.getDuration())
                .genre(entity.getGenre())
                .directorDto(directorConverter.toDto(entity.getDirector()))
                .writerDto(writerConverter.toDto(entity.getWriter()))
                .build();
    }

    @Override
    public Show toEntity(ShowDto dto) {
        return Show.builder()
                .id(dto.getId())
                .name(dto.getName())
                .duration(dto.getDuration())
                .genre(dto.getGenre())
                .director(directorConverter.toEntity(dto.getDirectorDto()))
                .writer(writerConverter.toEntity(dto.getWriterDto()))
                .build();
    }
}
