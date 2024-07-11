package com.master.theater.service.impl;

import com.master.theater.converter.impl.WriterConverter;
import com.master.theater.domain.Writer;
import com.master.theater.dto.WriterDto;
import com.master.theater.exception.EntityNotFoundException;
import com.master.theater.repository.WriterRepository;
import com.master.theater.service.WriterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WriterServiceImpl implements WriterService {

    private WriterRepository writerRepository;
    private WriterConverter writerConverter;

    public WriterServiceImpl(WriterRepository writerRepository, WriterConverter writerConverter) {
        this.writerRepository = writerRepository;
        this.writerConverter = writerConverter;
    }

    @Override
    public List<WriterDto> getAll() {
        return writerRepository.findAll().stream()
                .map(entity -> writerConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public WriterDto save(WriterDto writerDto) {
        Writer writer = writerConverter.toEntity(writerDto);
        writer = writerRepository.save(writer);
        return writerConverter.toDto(writer);
    }

    @Override
    public WriterDto update(WriterDto writerDto) throws EntityNotFoundException {
        Optional<Writer> writerPresent  = writerRepository.findById(writerDto.getId());
        if(writerPresent.isPresent()){
            Writer writer = writerPresent.get();
            writer.setName(writerDto.getName());
            writer.setSurname(writerDto.getSurname());
            writer = writerRepository.save(writer);
            return writerConverter.toDto(writer);
        }
        else {
            throw new EntityNotFoundException("Writer with id = " + writerDto.getId() +
                    " does not exist!");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Optional<Writer> writerPresent  = writerRepository.findById(id);
        if(writerPresent.isPresent()){
            Writer writer = writerPresent.get();
            writerRepository.delete(writer);
        }
        else {
            throw new EntityNotFoundException("Writer with id = " + id +
                    " does not exist!");
        }
    }

    @Override
    public WriterDto findById(Long id) throws EntityNotFoundException {
        Optional<Writer> writerPresent  = writerRepository.findById(id);
        if(writerPresent.isPresent()){
            Writer writer = writerPresent.get();
            return writerConverter.toDto(writer);
        }
        else {
            throw new EntityNotFoundException("Writer with id = " + id +
                    " does not exist!");
        }
    }
}
