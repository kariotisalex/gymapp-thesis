package com.alexkariotis.gymapp.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "membership")
public class Membership {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "presences")
    private int presences;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Fetch(FetchMode.JOIN) // TODO
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users user;

}
