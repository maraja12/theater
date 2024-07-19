package com.master.theater.domain;

import com.master.theater.domain.ids.EngagementId;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "engagement")
public class Engagement {
    @EmbeddedId
    private EngagementId id;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    @MapsId("actorId")
    private Actor actor;
    @ManyToOne
    @JoinColumn(name = "show_id")
    @MapsId("showId")
    private Show show;

    @NotEmpty(message = "Role is required!")
    @Column(name = "role")
    private String role;
    @NotNull(message = "Start date is required!")
    @PastOrPresent
    @Column(name = "start_date")
    private LocalDate startDate;
    @FutureOrPresent
    @Column(name = "end_date")
    private LocalDate endDate;
}
