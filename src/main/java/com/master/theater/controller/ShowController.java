package com.master.theater.controller;

import com.master.theater.dto.ShowDto;
import com.master.theater.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    private ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public ResponseEntity<List<ShowDto>> getAll(){
        List<ShowDto> shows = showService.getAll();
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ShowDto> save(@Valid @RequestBody ShowDto showDto){
        ShowDto show = showService.save(showDto);
        return new ResponseEntity<>(show, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ShowDto> update(@Valid @RequestBody ShowDto showDto){
        ShowDto show = showService.update(showDto);
        return new ResponseEntity<>(show, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        showService.delete(id);
        return new ResponseEntity<>("Show with id = " + id +" is removed!", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> findById(@PathVariable("id") Long id){
        ShowDto show = showService.findById(id);
        return new ResponseEntity<>(show, HttpStatus.FOUND);
    }
}
