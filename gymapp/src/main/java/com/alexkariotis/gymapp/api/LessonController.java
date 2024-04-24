package com.alexkariotis.gymapp.api;

import com.alexkariotis.gymapp.dto.lesson.LessonRequestDto;
import com.alexkariotis.gymapp.dto.lesson.LessonResponseDto;
import com.alexkariotis.gymapp.mapper.LessonMapper;
import com.alexkariotis.gymapp.service.LessonService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    private final LessonMapper lessonMapper;

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDto> getLessonById(@PathVariable("id") UUID lessonId) {
        return lessonService.getLessonById(lessonId)
                .map(lessonMapper::toLessonResponseDto)
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("Something went wrong! "
                        + ex.getMessage()));
    }

    @GetMapping
    public ResponseEntity<List<LessonResponseDto>> getAllLessons() {
        return lessonService.getAll()
                .map(lessons -> lessons.stream().map(lessonMapper::toLessonResponseDto)
                        .collect(Collectors.toList()))
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("Something went wrong! "
                        + ex.getMessage()));
    }

    @PostMapping
    public ResponseEntity<LessonResponseDto> create(@RequestBody LessonRequestDto lessonRequestDto) {
        return Try.of(() -> lessonMapper.toLesson(lessonRequestDto))
                .flatMap(lessonService::create)
                .map(lessonMapper::toLessonResponseDto)
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("Something went wrong! "
                        + ex.getMessage()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LessonResponseDto> update(@RequestBody LessonRequestDto lessonRequestDto){
        return Try.of(() -> lessonMapper.toLesson(lessonRequestDto))
                .flatMap(lessonService::update)
                .map(lessonMapper::toLessonResponseDto)
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("Something went wrong! "
                        + ex.getMessage()));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID lessonId) {
        return lessonService.getLessonById(lessonId)
                .flatMap(users -> lessonService.delete(lessonId))
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("Something went wrong" + ex.getMessage()));
    }


}
