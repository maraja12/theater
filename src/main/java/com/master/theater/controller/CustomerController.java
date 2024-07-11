package com.master.theater.controller;

import com.master.theater.dto.CustomerDto;
import com.master.theater.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll(){
        List<CustomerDto> customers = customerService.getAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody CustomerDto dto){
        CustomerDto customerDto = customerService.save(dto);
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable("id") Long id){
        customerService.delete(id);
        return new ResponseEntity<>("Customer with id = " + id + " is removed!", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") Long id){
        CustomerDto customerDto = customerService.findById(id);
        return new ResponseEntity<>(customerDto, HttpStatus.FOUND);
    }
    @PutMapping
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerDto dto){
        CustomerDto customerDto = customerService.update(dto);
        return new ResponseEntity<>(customerDto, HttpStatus.FOUND);
    }

}
