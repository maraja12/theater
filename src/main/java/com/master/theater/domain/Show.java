package com.master.theater.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "show")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name of show is required field!")
    @Column(name = "name")
    private String name;
    @NotNull(message = "Duration is required field!")
    @Column(name = "duration")
    private int duration;
    @Column(name = "genre")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Writer writer;
}
