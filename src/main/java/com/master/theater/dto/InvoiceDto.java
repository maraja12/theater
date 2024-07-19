package com.master.theater.dto;

import com.master.theater.domain.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InvoiceDto implements Serializable {

    private Long id;
    @NotNull
    private LocalDate date;
    private Status status;
    private CustomerDto customerDto;
}
