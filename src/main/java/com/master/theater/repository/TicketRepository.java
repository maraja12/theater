package com.master.theater.repository;

import com.master.theater.domain.Invoice;
import com.master.theater.domain.Show;
import com.master.theater.domain.Ticket;
import com.master.theater.domain.ids.TicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, TicketId> {

    @Query(value = "SELECT * FROM ticket WHERE CONVERT(date, date_time) = :purchaseDate", nativeQuery = true)
    List<Ticket> findByPurchaseDate(@Param("purchaseDate") LocalDate purchaseDate);
    Optional<List<Ticket>> findByShow(Show show);
    Optional<List<Ticket>> findByInvoice(Invoice invoice);
}
