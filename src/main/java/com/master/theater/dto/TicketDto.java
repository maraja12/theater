package com.master.theater.dto;

import com.master.theater.domain.ids.TicketId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto implements Serializable {

    private TicketId id;
    private InvoiceDto invoiceDto;
    @NotNull
    private String scene;
    @NotNull
    private String place;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull
    private BigDecimal price;
    private BigDecimal discount;
    private ShowDto showDto;

}
