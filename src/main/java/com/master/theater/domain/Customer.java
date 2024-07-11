package com.master.theater.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Email is mandatory!")
    @Email(message = "Email have to be valid!")
    //da li je unique vrednost proveravam tokom unosa novog kupca
    @Column(name = "email")
    private String email;
    @Size(min = 3, max = 8, message = "Password length must be between 3 and 8 characters!")
    @NotEmpty(message = "Password is mandatory!")
    @Column(name = "password", nullable = false)
    private String password;
    @NotEmpty(message = "Name is mandatory!")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Surname is mandatory!")
    @Column(name = "surname")
    private String surname;
    @Min(value = 16, message = "Customer should be at least 16 years old!")
    @Column(name = "age")
    private int age;


}
