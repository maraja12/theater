package com.master.theater.service;

import com.master.theater.dto.CustomerDto;
import com.master.theater.exception.EmailAlreadyExistsException;
import com.master.theater.exception.EntityNotFoundException;

import java.util.List;

public interface CustomerService{

    List<CustomerDto> getAll();
    CustomerDto save(CustomerDto customerDto) throws EmailAlreadyExistsException;
    CustomerDto update(CustomerDto customerDto) throws EmailAlreadyExistsException, EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
    CustomerDto findById(Long id) throws EntityNotFoundException;
}
