package com.master.theater.service;

import com.master.theater.dto.ShowDto;
import com.master.theater.exception.EntityNotFoundException;

import java.util.List;

public interface ShowService {

    List<ShowDto> getAll();
    ShowDto save(ShowDto showDto) throws EntityNotFoundException;
    ShowDto update(ShowDto showDto) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
    ShowDto findById(Long id) throws EntityNotFoundException;
}
