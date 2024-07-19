package com.master.theater.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto implements Serializable {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private int duration;
    private String genre;
    private DirectorDto directorDto;
    private WriterDto writerDto;
}
