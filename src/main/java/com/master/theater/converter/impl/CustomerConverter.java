package com.master.theater.converter.impl;

import com.master.theater.converter.DtoEntityConverter;
import com.master.theater.domain.Customer;
import com.master.theater.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements DtoEntityConverter<CustomerDto, Customer> {
    @Override
    public CustomerDto toDto(Customer entity) {
        return CustomerDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .name(entity.getName())
                .surname(entity.getSurname())
                .age(entity.getAge())
                .build();
    }

    @Override
    public Customer toEntity(CustomerDto dto) {
        return Customer.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .build();
    }
}
