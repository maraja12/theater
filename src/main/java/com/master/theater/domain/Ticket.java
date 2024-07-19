package com.master.theater.domain;

import com.master.theater.domain.ids.TicketId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @EmbeddedId
    private TicketId id;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @MapsId("invoiceId")
    private Invoice invoice;
    @NotEmpty(message = "Scene is required!")
    @Column(name = "scene")
    private String scene;
    @NotEmpty(message = "Place is required!")
    @Column(name = "place")
    private String place;
    @NotNull(message = "Date and time are required!")
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @NotNull(message = "Price is required!")
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "discount")
    private BigDecimal discount;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
}
