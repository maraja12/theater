package com.master.theater.service;

import com.master.theater.dto.WriterDto;
import com.master.theater.exception.EntityNotFoundException;

import java.util.List;

public interface WriterService {

    List<WriterDto> getAll();
    WriterDto save(WriterDto writerDto);
    WriterDto update(WriterDto writerDto) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
    WriterDto findById(Long id) throws EntityNotFoundException;
}
