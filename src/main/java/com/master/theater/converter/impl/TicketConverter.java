package com.master.theater.converter.impl;

import com.master.theater.converter.DtoEntityConverter;
import com.master.theater.domain.Ticket;
import com.master.theater.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter implements DtoEntityConverter<TicketDto, Ticket> {

    @Autowired
    private InvoiceConverter invoiceConverter;
    @Autowired
    private ShowConverter showConverter;
    @Override
    public TicketDto toDto(Ticket entity) {
        return TicketDto.builder()
                .id(entity.getId())
                .invoiceDto(invoiceConverter.toDto(entity.getInvoice()))
                .scene(entity.getScene())
                .place(entity.getPlace())
                .dateTime(entity.getDateTime())
                .price(entity.getPrice())
                .discount(entity.getDiscount())
                .showDto(showConverter.toDto(entity.getShow()))
                .build();
    }

    @Override
    public Ticket toEntity(TicketDto dto) {
        return Ticket.builder()
                .id(dto.getId())
                .invoice(invoiceConverter.toEntity(dto.getInvoiceDto()))
                .scene(dto.getScene())
                .place(dto.getPlace())
                .dateTime(dto.getDateTime())
                .price(dto.getPrice())
                .discount(dto.getDiscount())
                .show(showConverter.toEntity(dto.getShowDto()))
                .build();
    }
}
