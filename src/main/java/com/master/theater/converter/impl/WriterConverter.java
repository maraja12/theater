package com.master.theater.converter.impl;

import com.master.theater.converter.DtoEntityConverter;
import com.master.theater.domain.Writer;
import com.master.theater.dto.WriterDto;
import org.springframework.stereotype.Component;

@Component
public class WriterConverter implements DtoEntityConverter<WriterDto, Writer> {
    @Override
    public WriterDto toDto(Writer entity) {
        return WriterDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }

    @Override
    public Writer toEntity(WriterDto dto) {
        return Writer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .build();
    }
}
