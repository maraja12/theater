package com.master.theater.controller;

import com.master.theater.dto.DirectorDto;
import com.master.theater.service.DirectorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/director")
public class DirectorController {

    private DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<List<DirectorDto>> getAll(){
        List<DirectorDto> directors = directorService.getAll();
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DirectorDto> save(@Valid @RequestBody DirectorDto directorDto){
        DirectorDto director = directorService.save(directorDto);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<DirectorDto> update(@Valid @RequestBody DirectorDto directorDto){
        DirectorDto director = directorService.update(directorDto);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        directorService.delete(id);
        return new ResponseEntity<>("Director with id = " + id + " is removed!", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> findById(@PathVariable("id") Long id){
        DirectorDto director = directorService.findById(id);
        return new ResponseEntity<>(director, HttpStatus.FOUND);
    }
}
