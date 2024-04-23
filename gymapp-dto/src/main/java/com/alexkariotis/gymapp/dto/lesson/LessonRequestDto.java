package com.alexkariotis.gymapp.dto.lesson;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LessonRequestDto {

    private String name;
    private int durationMinutes;

}
