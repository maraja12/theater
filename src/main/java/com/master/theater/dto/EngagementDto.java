package com.master.theater.dto;

import com.master.theater.domain.ids.EngagementId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngagementDto implements Serializable {

    private EngagementId id;

    private ActorDto actorDto;

    private ShowDto showDto;
    @NotNull
    private String role;
    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;
}
