package com.master.theater.controller;

import com.master.theater.dto.InvoiceDto;
import com.master.theater.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAll(){
        List<InvoiceDto> invoices = invoiceService.getAll();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> save(@Valid @RequestBody InvoiceDto invoiceDto){
        InvoiceDto invoice = invoiceService.save(invoiceDto);
        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<InvoiceDto> update(@Valid @RequestBody InvoiceDto invoiceDto){
        InvoiceDto invoice = invoiceService.update(invoiceDto);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        invoiceService.delete(id);
        return new ResponseEntity<>("Invoice with id = " + id + " is removed!", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> findById(@PathVariable("id") Long id){
        InvoiceDto invoice = invoiceService.findById(id);
        return new ResponseEntity<>(invoice, HttpStatus.FOUND);
    }
}

