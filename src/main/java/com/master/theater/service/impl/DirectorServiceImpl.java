package com.master.theater.service.impl;

import com.master.theater.converter.impl.DirectorConverter;
import com.master.theater.domain.Director;
import com.master.theater.dto.DirectorDto;
import com.master.theater.exception.EntityNotFoundException;
import com.master.theater.repository.DirectorRepository;
import com.master.theater.service.DirectorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorService {

    private DirectorRepository directorRepository;
    private DirectorConverter directorConverter;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorConverter directorConverter) {
        this.directorRepository = directorRepository;
        this.directorConverter = directorConverter;
    }

    @Override
    public List<DirectorDto> getAll() {
        return directorRepository.findAll().stream()
                .map(entity -> directorConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDto save(DirectorDto directorDto) {
        Director director = directorConverter.toEntity(directorDto);
        director = directorRepository.save(director);
        return directorConverter.toDto(director);
    }

    @Override
    public DirectorDto update(DirectorDto directorDto) throws EntityNotFoundException {
        Optional<Director> directorPresent = directorRepository.findById(directorDto.getId());
        if(directorPresent.isPresent()){
             Director director = directorPresent.get();
             director.setName(directorDto.getName());
             director.setSurname(directorDto.getSurname());
             director = directorRepository.save(director);
             return directorConverter.toDto(director);
        }
        else{
            throw  new EntityNotFoundException("Director with id = " + directorDto.getId()
            + " does not exist!");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Optional<Director> directorPresent = directorRepository.findById(id);
        if(directorPresent.isPresent()){
            Director director = directorPresent.get();
            directorRepository.delete(director);
        }
        else{
            throw  new EntityNotFoundException("Director with id = " + id
                    + " does not exist!");
        }
    }

    @Override
    public DirectorDto findById(Long id) throws EntityNotFoundException {
        Optional<Director> directorPresent = directorRepository.findById(id);
        if(directorPresent.isPresent()){
            Director director = directorPresent.get();
            return directorConverter.toDto(director);
        }
        else{
            throw  new EntityNotFoundException("Director with id = " + id
                    + " does not exist!");
        }
    }
}
