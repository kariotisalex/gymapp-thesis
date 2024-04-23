package com.alexkariotis.gymapp.dto.lesson;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponseDto {

    private UUID id;
    private String name;
    private int durationMinutes;

}
