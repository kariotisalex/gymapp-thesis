package com.alexkariotis.gymapp.domain.entity;

import jakarta.persistence.*;
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
@Entity(name = "appointments")
public class Appointment {

    @Id
    @UuidGenerator
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @OneToOne
    @JoinColumn(name = "timetable_id")
    private TimeTable timeTable;

    @Column(name = "created_at")
    private LocalDateTime createAt;

}
