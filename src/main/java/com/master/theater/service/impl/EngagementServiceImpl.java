package com.master.theater.service.impl;

import com.master.theater.converter.impl.ActorConverter;
import com.master.theater.converter.impl.EngagementConverter;
import com.master.theater.converter.impl.ShowConverter;
import com.master.theater.domain.Actor;
import com.master.theater.domain.Engagement;
import com.master.theater.domain.Show;
import com.master.theater.dto.EngagementDto;
import com.master.theater.exception.EntityAlreadyExistException;
import com.master.theater.exception.EntityNotFoundException;
import com.master.theater.repository.ActorRepository;
import com.master.theater.repository.EngagementRepository;
import com.master.theater.repository.ShowRepository;
import com.master.theater.service.EngagementService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EngagementServiceImpl implements EngagementService {

    private EngagementRepository engagementRepository;
    private EngagementConverter engagementConverter;

    private ActorRepository actorRepository;
    private ActorConverter actorConverter;
    private ShowRepository showRepository;
    private ShowConverter showConverter;

    public EngagementServiceImpl(EngagementRepository engagementRepository,
                                 EngagementConverter engagementConverter,
                                 ActorRepository actorRepository, ActorConverter actorConverter,
                                 ShowRepository showRepository, ShowConverter showConverter) {
        this.engagementRepository = engagementRepository;
        this.engagementConverter = engagementConverter;
        this.actorRepository = actorRepository;
        this.actorConverter = actorConverter;
        this.showRepository = showRepository;
        this.showConverter = showConverter;
    }

    @Override
    public List<EngagementDto> getAll() {
        return engagementRepository.findAll().stream()
                .map(entity -> engagementConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public EngagementDto save(EngagementDto engagementDto) throws EntityNotFoundException {
        Optional<Actor> actorPres = actorRepository.findById(engagementDto.getActorDto().getId());
        Optional<Show> showPres = showRepository.findById(engagementDto.getShowDto().getId());
        if(actorPres.isEmpty()){
            throw new EntityNotFoundException("Actor with id = " + engagementDto.getActorDto().getId() +
                    " does not exist!");
        } else if (showPres.isEmpty()) {
            throw new EntityNotFoundException("Show with id = " + engagementDto.getShowDto().getId() +
                    " does not exist!");
        }
        else{
            Actor actor = actorPres.get();
            Show show = showPres.get();
            //prvo proveravam da li porstoji glumac s ovim kljucem, ne smeju da se ponavlja
            Optional<Engagement> engagPres = engagementRepository.findByActorAndShow(actor, show);
            if(engagPres.isPresent()){
                throw new EntityAlreadyExistException("Engagement between this actor and show already exists!");
            }
            //proveravam da li vec postoji glumac koji igra datu ulogu za datu predstavu,
            // i ako postoji, setujem end date na null
            Optional<List<Engagement>> roleAndShow =
                    engagementRepository.findByRoleAndShow(engagementDto.getRole(), show);
            if(roleAndShow.isPresent()){
                List<Engagement> previousEng = roleAndShow.get();
                for(Engagement eng : previousEng){
                    if(eng.getEndDate() == null){
                       eng.setEndDate(LocalDate.now());
                    }
                }
            }
            Engagement engagement = engagementConverter.toEntity(engagementDto);
            //engagement.setEndDate(null);
            engagement = engagementRepository.save(engagement);
            return engagementConverter.toDto(engagement);
        }
    }

    @Override
    public EngagementDto update(EngagementDto engagementDto) throws EntityNotFoundException {
        Optional<Actor> actorPresent = actorRepository.findById(engagementDto.getActorDto().getId());
        Optional<Show> showPresent = showRepository.findById(engagementDto.getShowDto().getId());
        if(actorPresent.isPresent()){
            Actor actor = actorPresent.get();
            if(showPresent.isPresent()){
                Show show = showPresent.get();
                Optional<Engagement> engagPresent = engagementRepository.findByActorAndShow(actor, show);
                if(engagPresent.isPresent()){
                    Engagement engagement = engagPresent.get();
                    engagement.setRole(engagementDto.getRole());
                    engagement.setStartDate(engagementDto.getStartDate());
                    engagement.setEndDate(engagementDto.getEndDate());
                    engagement = engagementRepository.save(engagement);
                    return engagementConverter.toDto(engagement);
                }
                else{
                    throw new EntityNotFoundException("Engagement between this actor and show"+
                            " does not exist!");
                }
            }
            else{
                throw new EntityNotFoundException("Show with id = " + engagementDto.getShowDto().getId() +
                        " does not exist!");
            }
        }
        else{
            throw new EntityNotFoundException("Actor with id = " + engagementDto.getActorDto().getId() +
                    " does not exist!");
        }
    }

    @Override
    public EngagementDto findByActorIdAndShowId(Long actorId, Long showId) throws EntityNotFoundException {
        Optional<Actor> actorPresent = actorRepository.findById(actorId);
        Optional<Show> showPresent = showRepository.findById(showId);
        if(actorPresent.isPresent()){
            Actor actor = actorPresent.get();
            if(showPresent.isPresent()){
                Show show = showPresent.get();
                Optional<Engagement> engagPresent = engagementRepository.findByActorAndShow(actor, show);
                if(engagPresent.isPresent()){
                    Engagement engagement = engagPresent.get();
                    return engagementConverter.toDto(engagement);
                }
                else{
                    throw new EntityNotFoundException("Engagement between this actor and show"+
                            " does not exist!");
                }
            }
            else{
                throw new EntityNotFoundException("Show with id = " + showId +
                        " does not exist!");
            }
        }
        else{
            throw new EntityNotFoundException("Actor with id = " + actorId +
                    " does not exist!");
        }
    }

    @Override
    public List<EngagementDto> findByActorId(Long actorId) throws EntityNotFoundException {
        Optional<Actor> actorPresent = actorRepository.findById(actorId);
        if(actorPresent.isPresent()){
            Actor actor = actorPresent.get();
                Optional<List<Engagement>> engPres = engagementRepository.findByActor(actor);
                if(engPres.isPresent()){
                    List<Engagement> engagements = engPres.get();
                    return engagements.stream()
                            .map(entity -> engagementConverter.toDto(entity))
                            .collect(Collectors.toList());
                }
                else{
                    throw new EntityNotFoundException("There are no engagements for " +
                            "actor with id = " + actorId
                            );
                }
        }
        else{
            throw new EntityNotFoundException("Actor with id = " + actorId +
                    " does not exist!");
        }
    }

    @Override
    public List<EngagementDto> findByShowId(Long showId) throws EntityNotFoundException {
        Optional<Show> showPresent = showRepository.findById(showId);
        if(showPresent.isPresent()){
            Show show = showPresent.get();
            Optional<List<Engagement>> engPres = engagementRepository.findByShow(show);
            if(engPres.isPresent()){
                List<Engagement> engagements = engPres.get();
                return engagements.stream()
                        .map(entity -> engagementConverter.toDto(entity))
                        .collect(Collectors.toList());
            }
            else{
                throw new EntityNotFoundException("There are no engagements for " +
                        "show with id = " + showId
                );
            }
        }
        else{
            throw new EntityNotFoundException("Show with id = " + showId +
                    " does not exist!");
        }
    }

    @Override
    public List<EngagementDto> findCurrentEngagements() {
        List<EngagementDto> engagements = getAll();
        List<EngagementDto> active = new ArrayList<>();
        for (EngagementDto engagement : engagements) {
            if (engagement.getEndDate() == null) {
                active.add(engagement);
            }
        }
        return active;
    }

    @Override
    public List<EngagementDto> findCurrentByActorId(Long actorId) throws EntityNotFoundException {
        List<EngagementDto> engagements = findByActorId(actorId);
        List<EngagementDto> active = new ArrayList<>();
        for (EngagementDto engagement : engagements) {
            if (engagement.getEndDate() == null) {
                active.add(engagement);
            }
        }
        return active;
    }

    @Override
    public List<EngagementDto> findCurrentByShowId(Long showId) throws EntityNotFoundException {
        List<EngagementDto> engagements = findByShowId(showId);
        List<EngagementDto> active = new ArrayList<>();
        for (EngagementDto engagement : engagements) {
            if (engagement.getEndDate() == null) {
                active.add(engagement);
            }
        }
        return active;
    }


}
