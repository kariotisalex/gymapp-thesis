package com.alexkariotis.gymapp.api;

import com.alexkariotis.gymapp.mapper.LessonMapper;
import com.alexkariotis.gymapp.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    private final LessonMapper lessonMapper;



}
