package com.alexkariotis.gymapp.service;

import com.alexkariotis.gymapp.domain.entity.Lesson;
import com.alexkariotis.gymapp.domain.repository.LessonRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public Try<Lesson> create(Lesson lesson) {
        lesson.setId(UUID.randomUUID());
        lesson.setCreatedAt(LocalDateTime.now());
        lesson.setUpdatedAt(LocalDateTime.now());
        return Try.of(() -> lessonRepository.save(lesson));
    }

    public Try<List<Lesson>> getAll() {
        return Try.of(lessonRepository::findAll);
    }

    public Try<Lesson> getLessonById(UUID lessonId) {
        return Try.of(() -> Option.ofOptional(lessonRepository.findById(lessonId))
                .getOrElseThrow(() -> new RuntimeException("There is no lesson with id " + lessonId )));
    }

    public Try<Lesson> update(Lesson lesson) {
        lesson.setUpdatedAt(LocalDateTime.now());
        return Try.of(() -> lessonRepository.save(lesson));
    }

    public Try<Void> delete(UUID lessonId) {
        return Try.run(() -> lessonRepository.deleteById(lessonId));
    }
}
