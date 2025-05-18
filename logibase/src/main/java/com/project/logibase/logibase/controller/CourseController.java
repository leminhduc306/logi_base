package com.project.logibase.logibase.controller;


import com.project.logibase.logibase.dto.api.ApiResponse;
import com.project.logibase.logibase.dto.request.CreateCourseRequest;
import com.project.logibase.logibase.dto.response.CourseResponse;
import com.project.logibase.logibase.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor

public class CourseController {

    private CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(@RequestBody CreateCourseRequest request) {
        CourseResponse created = courseService.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<CourseResponse>builder()
                        .code(201)
                        .message("Course created successfully")
                        .data(created)
                        .build()
        );
    }

}
