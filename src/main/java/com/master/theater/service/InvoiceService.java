package com.master.theater.service;

import com.master.theater.dto.InvoiceDto;
import com.master.theater.exception.EntityNotFoundException;

import java.util.List;

public interface InvoiceService {

    List<InvoiceDto> getAll();
    InvoiceDto save(InvoiceDto invoiceDto) throws EntityNotFoundException;
    InvoiceDto update(InvoiceDto invoiceDto) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
    InvoiceDto findById(Long id) throws EntityNotFoundException;
}
