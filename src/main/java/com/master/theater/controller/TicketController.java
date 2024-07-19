package com.master.theater.controller;

import com.master.theater.dto.TicketDto;
import com.master.theater.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAll(){
        List<TicketDto> tickets = ticketService.getAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @GetMapping("/date/{date}")
    public ResponseEntity<List<TicketDto>> findByPurchaseDate(@PathVariable("date")LocalDate date){
        List<TicketDto> ticket = ticketService.findByPurchaseDate(date);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<TicketDto> save(@RequestBody @Valid TicketDto ticketDto){
        TicketDto ticket = ticketService.save(ticketDto);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }
    @GetMapping("/show/{show-id}")
    public ResponseEntity<List<TicketDto>> findByShow(@PathVariable("show-id") Long showId){
        List<TicketDto> tickets = ticketService.findByShow(showId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @GetMapping("/id/{id}/invoice/{invoice-id}")
    public ResponseEntity<TicketDto> findById(
            @PathVariable("id") Long id, @PathVariable("invoice-id")Long invoiceId){
        TicketDto ticket = ticketService.findById(id, invoiceId);
        return new ResponseEntity<>(ticket, HttpStatus.FOUND);
    }
}
