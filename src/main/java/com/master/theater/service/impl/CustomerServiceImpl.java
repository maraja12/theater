package com.master.theater.service.impl;

import com.master.theater.converter.impl.CustomerConverter;
import com.master.theater.domain.Customer;
import com.master.theater.dto.CustomerDto;
import com.master.theater.exception.EmailAlreadyExistsException;
import com.master.theater.exception.EntityNotFoundException;
import com.master.theater.repository.CustomerRepository;
import com.master.theater.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerConverter customerConverter;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.findAll()
                .stream().map(entity -> customerConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) throws EmailAlreadyExistsException{
        Optional<Customer> cust = customerRepository.findByEmail(customerDto.getEmail());
        if(cust.isPresent()){
            throw new EmailAlreadyExistsException("Customer with email: " + customerDto.getEmail() +
                    " already exists!");
        }
        Customer customer = customerConverter.toEntity(customerDto);
        customer = customerRepository.save(customer);
        return customerConverter.toDto(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) throws EmailAlreadyExistsException, EntityNotFoundException {
        Optional<Customer> presentCustomer = customerRepository.findById(customerDto.getId());
        if(presentCustomer.isPresent()){
            Customer customer = presentCustomer.get();
            Optional<Customer> cust = customerRepository.findByEmail(customerDto.getEmail());
            if(cust.isPresent()){
                throw new EmailAlreadyExistsException("Customer with email: " + customerDto.getEmail() +
                        " already exists!");
            }
            else{
                customer.setEmail(customerDto.getEmail());
            }
            customer.setPassword(customerDto.getPassword());
            customer.setName(customerDto.getName());
            customer.setSurname(customerDto.getSurname());
            customer.setAge(customerDto.getAge());
            return customerConverter.toDto(customer);
        }
        else{
            throw new EntityNotFoundException("Customer with id = " + customerDto.getId() +
                    " does not exist!");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException{
        Optional<Customer> cust = customerRepository.findById(id);
        if(cust.isPresent()){
            Customer customer = cust.get();
            customerRepository.delete(customer);
        }
        else{
            throw new EntityNotFoundException("Customer with id = " + id +
                    " does not exist!");
        }
    }

    @Override
    public CustomerDto findById(Long id) throws EntityNotFoundException{
        Optional<Customer> cust = customerRepository.findById(id);
        if(cust.isPresent()){
            Customer customer = cust.get();
            return customerConverter.toDto(customer);
        }
        else{
            throw new EntityNotFoundException("Customer with id = " + id +
                    " does not exist!");
        }
    }
}
