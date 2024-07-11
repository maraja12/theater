package com.master.theater.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorDto implements Serializable {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
}
