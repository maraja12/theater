package com.master.theater.controller;

import com.master.theater.dto.EngagementDto;
import com.master.theater.service.EngagementService;
import jakarta.persistence.MapsId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engagement")
public class EngagementController {

    private EngagementService engagementService;

    public EngagementController(EngagementService engagementService) {
        this.engagementService = engagementService;
    }

    @GetMapping
    public ResponseEntity<List<EngagementDto>> getAll(){
        List<EngagementDto> engagements = engagementService.getAll();
        return new ResponseEntity<>(engagements, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EngagementDto> save(@Valid @RequestBody EngagementDto engagementDto){
        EngagementDto engagement = engagementService.save(engagementDto);
        return new ResponseEntity<>(engagement, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<EngagementDto> update(@Valid @RequestBody EngagementDto engagementDto){
        EngagementDto engagement = engagementService.update(engagementDto);
        return new ResponseEntity<>(engagement, HttpStatus.OK);
    }
    @GetMapping("/actor/{actor-id}/show/{show-id}")
    public ResponseEntity<EngagementDto> findEngagemnt(
            @PathVariable("actor-id")Long actorId,
            @PathVariable("show-id")Long showId){
                EngagementDto engagement = engagementService.findByActorIdAndShowId(actorId, showId);
                return new ResponseEntity<>(engagement, HttpStatus.FOUND);
    }
    @GetMapping("/actor/{actor-id}")
    public ResponseEntity<List<EngagementDto>> findByActor(@PathVariable("actor-id")Long id){
        List<EngagementDto> engagements = engagementService.findByActorId(id);
        return new ResponseEntity<>(engagements, HttpStatus.OK);
    }

    @GetMapping("/show/{show-id}")
    public ResponseEntity<List<EngagementDto>> findByShow(@PathVariable("show-id")Long id){
        List<EngagementDto> engagements = engagementService.findByShowId(id);
        return new ResponseEntity<>(engagements, HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<List<EngagementDto>> findCurrentEngagements(){
        List<EngagementDto> engagements = engagementService.findCurrentEngagements();
        return new ResponseEntity<>(engagements, HttpStatus.OK);
    }
    @GetMapping("/current/actor/{actor-id}")
    public ResponseEntity<List<EngagementDto>> findCurrentByActor(@PathVariable("actor-id")Long id){
        List<EngagementDto> engagements = engagementService.findCurrentByActorId(id);
        return new ResponseEntity<>(engagements, HttpStatus.OK);
    }
    @GetMapping("/current/show/{show-id}")
    public ResponseEntity<List<EngagementDto>> findCurrentByShow(@PathVariable("show-id")Long id){
        List<EngagementDto> engagements = engagementService.findCurrentByShowId(id);
        return new ResponseEntity<>(engagements, HttpStatus.OK);
    }

}
