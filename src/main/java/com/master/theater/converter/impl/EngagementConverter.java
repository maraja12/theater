package com.master.theater.converter.impl;

import com.master.theater.converter.DtoEntityConverter;
import com.master.theater.domain.Engagement;
import com.master.theater.dto.EngagementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EngagementConverter implements DtoEntityConverter<EngagementDto, Engagement> {

    @Autowired
    private ActorConverter actorConverter;
    @Autowired
    private ShowConverter showConverter;

    @Override
    public EngagementDto toDto(Engagement entity) {
        return EngagementDto.builder()
                .id(entity.getId())
                .actorDto(actorConverter.toDto(entity.getActor()))
                .showDto(showConverter.toDto(entity.getShow()))
                .role(entity.getRole())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }

    @Override
    public Engagement toEntity(EngagementDto dto) {
        return Engagement.builder()
                .id(dto.getId())
                .actor(actorConverter.toEntity(dto.getActorDto()))
                .show(showConverter.toEntity(dto.getShowDto()))
                .role(dto.getRole())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
