package com.alexkariotis.gymapp.mapper;

import com.alexkariotis.gymapp.domain.entity.Lesson;
import com.alexkariotis.gymapp.dto.lesson.LessonRequestDto;
import com.alexkariotis.gymapp.dto.lesson.LessonResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LessonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Lesson toLesson(LessonRequestDto lessonRequestDto);

    LessonResponseDto toLessonResponseDto(Lesson lesson);
}
