package com.master.theater.service.impl;

import com.master.theater.converter.impl.InvoiceConverter;
import com.master.theater.converter.impl.ShowConverter;
import com.master.theater.converter.impl.TicketConverter;
import com.master.theater.domain.Invoice;
import com.master.theater.domain.Show;
import com.master.theater.domain.Ticket;
import com.master.theater.domain.ids.TicketId;
import com.master.theater.dto.TicketDto;
import com.master.theater.exception.EntityNotFoundException;
import com.master.theater.repository.InvoiceRepository;
import com.master.theater.repository.ShowRepository;
import com.master.theater.repository.TicketRepository;
import com.master.theater.service.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private TicketConverter ticketConverter;
    private InvoiceRepository invoiceRepository;
    private InvoiceConverter invoiceConverter;
    private ShowRepository showRepository;
    private ShowConverter showConverter;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             TicketConverter ticketConverter,
                             InvoiceRepository invoiceRepository,
                             InvoiceConverter invoiceConverter,
                             ShowRepository showRepository,
                             ShowConverter showConverter) {
        this.ticketRepository = ticketRepository;
        this.ticketConverter = ticketConverter;
        this.invoiceRepository = invoiceRepository;
        this.invoiceConverter = invoiceConverter;
        this.showRepository = showRepository;
        this.showConverter = showConverter;
    }

    @Override
    public List<TicketDto> getAll() {
        return ticketRepository.findAll().stream()
                .map(entity -> ticketConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto save(TicketDto ticketDto) throws EntityNotFoundException {
        Optional<Invoice> invoicePres = invoiceRepository.findById(ticketDto.getInvoiceDto().getId());
        Optional<Show> showPres = showRepository.findById(ticketDto.getShowDto().getId());
        if(invoicePres.isPresent()){
            Invoice invoice = invoicePres.get();
            Optional<List<Ticket>> ticketsInvoice = ticketRepository.findByInvoice(invoice);
            if(showPres.isPresent()){
                Ticket ticket = ticketConverter.toEntity(ticketDto);
                if(ticketsInvoice.isPresent() && ticketsInvoice.get().size() > 0){
                    List<Ticket> tickets = ticketsInvoice.get();
                    Ticket lastTicket = tickets.get(tickets.size()-1);
                    TicketId lastId = lastTicket.getId();
                    TicketId newId = new TicketId(lastId.getId()+1, invoice.getId());
                    ticket.setId(newId);
                }
                else{
                    TicketId newId = new TicketId(1L, invoice.getId());
                    ticket.setId(newId);
                }
                ticket = ticketRepository.save(ticket);
                return ticketConverter.toDto(ticket);
            }
            else{
                throw new EntityNotFoundException("Show with id = " + ticketDto.getShowDto().getId()+
                        " does not exist!");
            }
        }
        else{
            throw new EntityNotFoundException("Invoice with id = " + ticketDto.getInvoiceDto().getId()+
                    " does not exist!");
        }
    }

    @Override
    public List<TicketDto> findByPurchaseDate(LocalDate date) throws EntityNotFoundException {
        List<Ticket> tickets =ticketRepository.findByPurchaseDate(date);
        return tickets.stream().map(ticket -> ticketConverter.toDto(ticket)).collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findByShow(Long showId) throws EntityNotFoundException {
        Optional<Show> showPres = showRepository.findById(showId);
        if(showPres.isPresent()){
            Show show = showPres.get();
            Optional<List<Ticket>> ticketsPres = ticketRepository.findByShow(show);
            if(ticketsPres.isPresent()){
                List<Ticket> tickets = ticketsPres.get();
                return tickets.stream().map(ticket -> ticketConverter.toDto(ticket)).collect(Collectors.toList());
            }
            else{
                throw new EntityNotFoundException("There are no bought tickets for the show: " + show.getName());
            }
        }
        else{
            throw new EntityNotFoundException("Show with id = " + showId + " does not exist!");
        }
    }

    @Override
    public TicketDto findById(Long ticketId, Long invoiceId) throws EntityNotFoundException {
        TicketId id = new TicketId(ticketId, invoiceId);
        Optional<Ticket> ticketPres = ticketRepository.findById(id);
        if(ticketPres.isPresent()){
            Ticket ticket = ticketPres.get();
            return ticketConverter.toDto(ticket);
        }
        else{
            throw new EntityNotFoundException("Ticket with id = " + id + " does not exist!");
        }
    }
}
