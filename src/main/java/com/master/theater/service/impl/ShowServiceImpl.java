package com.master.theater.service.impl;

import com.master.theater.converter.impl.DirectorConverter;
import com.master.theater.converter.impl.ShowConverter;
import com.master.theater.converter.impl.WriterConverter;
import com.master.theater.domain.Director;
import com.master.theater.domain.Show;
import com.master.theater.domain.Writer;
import com.master.theater.dto.ShowDto;
import com.master.theater.exception.EntityNotFoundException;
import com.master.theater.repository.DirectorRepository;
import com.master.theater.repository.ShowRepository;
import com.master.theater.repository.WriterRepository;
import com.master.theater.service.ShowService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    private ShowRepository showRepository;
    private ShowConverter showConverter;
    private DirectorRepository directorRepository;
    private DirectorConverter directorConverter;
    private WriterRepository writerRepository;
    private WriterConverter writerConverter;

    public ShowServiceImpl(ShowRepository showRepository,
                           ShowConverter showConverter,
                           DirectorRepository directorRepository,
                           DirectorConverter directorConverter,
                           WriterRepository writerRepository,
                           WriterConverter writerConverter) {
        this.showRepository = showRepository;
        this.showConverter = showConverter;
        this.directorRepository = directorRepository;
        this.directorConverter = directorConverter;
        this.writerRepository = writerRepository;
        this.writerConverter = writerConverter;
    }

    @Override
    public List<ShowDto> getAll() {
        return showRepository.findAll().stream()
                .map(entity -> showConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public ShowDto save(ShowDto showDto) throws EntityNotFoundException {
        Optional<Director> director = directorRepository.findById(showDto.getDirectorDto().getId());
        Optional<Writer> writer = writerRepository.findById(showDto.getWriterDto().getId());
        if(director.isEmpty())
            throw new EntityNotFoundException("Director with id = " +
                    showDto.getDirectorDto().getId() + " does not exist!");
        else if (writer.isEmpty()) {
                throw new EntityNotFoundException("Writer with id = " +
                        showDto.getWriterDto().getId() + " does not exist!");
        }
        else{
            Show show = showConverter.toEntity(showDto);
            show = showRepository.save(show);
            return showConverter.toDto(show);
        }
    }

    @Override
    public ShowDto update(ShowDto showDto) throws EntityNotFoundException {
        Optional<Director> director = directorRepository.findById(showDto.getDirectorDto().getId());
        Optional<Writer> writer = writerRepository.findById(showDto.getWriterDto().getId());
        Optional<Show> showPresent = showRepository.findById(showDto.getId());
        if(showPresent.isPresent()){
            Show show = showPresent.get();
            if(director.isPresent()){
                if(writer.isPresent()){
                    show.setName(showDto.getName());
                    show.setDuration(showDto.getDuration());
                    show.setGenre(showDto.getGenre());
                    show.setDirector(directorConverter.toEntity(showDto.getDirectorDto()));
                    show.setWriter(writerConverter.toEntity(showDto.getWriterDto()));
                    show = showRepository.save(show);
                    return showConverter.toDto(show);
                }
                else{
                    throw new EntityNotFoundException("Writer with id = " +
                            showDto.getWriterDto().getId() + " does not exist!");
                }
            }
            else{
                throw new EntityNotFoundException("Director with id = " +
                        showDto.getDirectorDto().getId() + " does not exist!");
            }
        }
        else{
            throw new EntityNotFoundException("Show with id = " +
                    showDto.getId() + " does not exist!");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Optional<Show> showPresent = showRepository.findById(id);
        if(showPresent.isPresent()){
            Show show = showPresent.get();
            showRepository.delete(show);
        }
        else{
            throw new EntityNotFoundException("Show with id = " +
                    id + " does not exist!");
        }
    }

    @Override
    public ShowDto findById(Long id) throws EntityNotFoundException {
        Optional<Show> showPresent = showRepository.findById(id);
        if(showPresent.isPresent()){
            Show show = showPresent.get();
            return showConverter.toDto(show);
        }
        else{
            throw new EntityNotFoundException("Show with id = " +
                    id + " does not exist!");
        }
    }
}
