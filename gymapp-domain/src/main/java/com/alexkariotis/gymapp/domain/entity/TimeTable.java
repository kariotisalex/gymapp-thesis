package com.alexkariotis.gymapp.domain.entity;

import jakarta.persistence.*;
import jakarta.websocket.OnError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "time_table")
public class TimeTable {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

}
