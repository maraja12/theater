package com.master.theater.service.impl;

import com.master.theater.converter.impl.CustomerConverter;
import com.master.theater.converter.impl.InvoiceConverter;
import com.master.theater.domain.Customer;
import com.master.theater.domain.Invoice;
import com.master.theater.dto.InvoiceDto;
import com.master.theater.exception.EntityNotFoundException;
import com.master.theater.repository.CustomerRepository;
import com.master.theater.repository.InvoiceRepository;
import com.master.theater.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceConverter invoiceConverter;
    private CustomerConverter customerConverter;
    private CustomerRepository customerRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              InvoiceConverter invoiceConverter,
                              CustomerConverter customerConverter,
                              CustomerRepository customerRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceConverter = invoiceConverter;
        this.customerConverter = customerConverter;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<InvoiceDto> getAll() {
        return invoiceRepository.findAll().stream()
                .map(entity -> invoiceConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto save(InvoiceDto invoiceDto) throws EntityNotFoundException{
        Optional<Customer> customer = customerRepository.findById(invoiceDto.getCustomerDto().getId());
        if(customer.isPresent()){
            Invoice invoice = invoiceConverter.toEntity(invoiceDto);
            invoice = invoiceRepository.save(invoice);
            return invoiceConverter.toDto(invoice);
        }
        else{
            throw new EntityNotFoundException("Customer with id = " + invoiceDto.getCustomerDto().getId() +
                    " does not exist!");
        }
    }

    @Override
    public InvoiceDto update(InvoiceDto invoiceDto) throws EntityNotFoundException {
        Optional<Invoice> invoicePresent = invoiceRepository.findById(invoiceDto.getId());
        Optional<Customer> customer = customerRepository.findById(invoiceDto.getCustomerDto().getId());
        if(invoicePresent.isPresent()){
            if(customer.isPresent()) {
                Invoice invoice = invoicePresent.get();
                invoice.setDate(invoiceDto.getDate());
                invoice.setStatus(invoiceDto.getStatus());
                invoice.setCustomer(customerConverter.toEntity(invoiceDto.getCustomerDto()));
                invoice = invoiceRepository.save(invoice);
                return invoiceConverter.toDto(invoice);
            }
            else{
                throw new EntityNotFoundException("Customer with id = " + invoiceDto.getCustomerDto().getId() +
                        " does not exist!");
            }
        }
        else{
            throw new EntityNotFoundException("Invoice with id = " + invoiceDto.getId() +
                    " does not exist!");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Optional<Invoice> invoicePresent = invoiceRepository.findById(id);
        if(invoicePresent.isPresent()){
            Invoice invoice = invoicePresent.get();
            invoiceRepository.delete(invoice);
        }
        else{
            throw new EntityNotFoundException("Invoice with id = " + id +
                    " does not exist!");
        }
    }

    @Override
    public InvoiceDto findById(Long id) throws EntityNotFoundException {
        Optional<Invoice> invoicePresent = invoiceRepository.findById(id);
        if(invoicePresent.isPresent()){
            Invoice invoice = invoicePresent.get();
            return invoiceConverter.toDto(invoice);
        }
        else{
            throw new EntityNotFoundException("Invoice with id = " + id +
                    " does not exist!");
        }
    }
}
