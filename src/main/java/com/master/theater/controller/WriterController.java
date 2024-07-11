package com.master.theater.controller;

import com.master.theater.dto.WriterDto;
import com.master.theater.service.WriterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writer")
public class WriterController {

    private WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @GetMapping
    public ResponseEntity<List<WriterDto>> getAll(){
        List<WriterDto> writers = writerService.getAll();
        return new ResponseEntity<>(writers, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<WriterDto> save(@Valid @RequestBody WriterDto actorDto){
        WriterDto writer = writerService.save(actorDto);
        return new ResponseEntity<>(writer, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(Long id){
        writerService.delete(id);
        return new ResponseEntity<>("Writer with id = " + id + " is removed!" , HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<WriterDto> update(@Valid @RequestBody WriterDto actorDto){
        WriterDto writer = writerService.update(actorDto);
        return new ResponseEntity<>(writer, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<WriterDto> findById(@PathVariable("id") Long id){
        WriterDto writer = writerService.findById(id);
        return new ResponseEntity<>(writer, HttpStatus.FOUND);
    }

}
