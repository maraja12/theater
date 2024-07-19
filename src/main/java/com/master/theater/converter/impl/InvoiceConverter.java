package com.master.theater.converter.impl;

import com.master.theater.converter.DtoEntityConverter;
import com.master.theater.domain.Invoice;
import com.master.theater.dto.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter implements DtoEntityConverter<InvoiceDto, Invoice> {

    @Autowired
    private CustomerConverter customerConverter;
    @Override
    public Invoice toEntity(InvoiceDto dto) {
        return Invoice.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .status(dto.getStatus())
                .customer(customerConverter.toEntity(dto.getCustomerDto()))
                .build();
    }

    @Override
    public InvoiceDto toDto(Invoice entity) {
        return InvoiceDto.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .status(entity.getStatus())
                .customerDto(customerConverter.toDto(entity.getCustomer()))
                .build();
    }
}
