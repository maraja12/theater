package com.master.theater.service;

import com.master.theater.dto.TicketDto;
import com.master.theater.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {

    List<TicketDto> getAll();
    TicketDto save(TicketDto ticketDto) throws EntityNotFoundException;
    List<TicketDto> findByPurchaseDate(LocalDate date) throws EntityNotFoundException;
    List<TicketDto> findByShow(Long showId) throws EntityNotFoundException;
    TicketDto findById(Long ticketId, Long invoiceId) throws EntityNotFoundException;

}
