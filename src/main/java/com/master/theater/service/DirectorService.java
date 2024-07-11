package com.master.theater.service;

import com.master.theater.dto.DirectorDto;
import com.master.theater.exception.EntityNotFoundException;

import java.util.List;

public interface DirectorService {

    List<DirectorDto> getAll();
    DirectorDto save(DirectorDto directorDto);
    DirectorDto update(DirectorDto directorDto) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
    DirectorDto findById(Long id) throws EntityNotFoundException;
}
