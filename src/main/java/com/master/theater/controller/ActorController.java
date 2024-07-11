package com.master.theater.controller;

import com.master.theater.dto.ActorDto;
import com.master.theater.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @GetMapping
    public ResponseEntity<List<ActorDto>> getAll(){
        List<ActorDto> actors = actorService.getAll();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ActorDto> save(@Valid @RequestBody ActorDto actorDto){
        ActorDto actor = actorService.save(actorDto);
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(Long id){
        actorService.delete(id);
        return new ResponseEntity<>("Actor with id = " + id + " is removed!" , HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ActorDto> update(@Valid @RequestBody ActorDto actorDto){
        ActorDto actor = actorService.update(actorDto);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> findById(@PathVariable("id") Long id){
        ActorDto actor = actorService.findById(id);
        return new ResponseEntity<>(actor, HttpStatus.FOUND);
    }

}
