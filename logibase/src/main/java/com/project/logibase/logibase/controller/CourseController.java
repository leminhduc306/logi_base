package com.project.logibase.logibase.controller;


import com.project.logibase.logibase.dto.api.ApiResponse;
import com.project.logibase.logibase.dto.page.PageResponse;
import com.project.logibase.logibase.dto.request.CreateCourseRequest;
import com.project.logibase.logibase.dto.request.UpdateCourseRequest;
import com.project.logibase.logibase.dto.response.CourseResponse;
import com.project.logibase.logibase.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor

public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(@Valid @RequestBody CreateCourseRequest request) {
        courseService.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<CourseResponse>builder()
                        .code(201)
                        .message("Course created successfully")
                        .data(null)
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(@Valid @RequestBody UpdateCourseRequest request) {
        CourseResponse response = courseService.updateCourse(request);
        return ResponseEntity.ok(
                ApiResponse.<CourseResponse>builder()
                        .code(200)
                        .message("Course updated successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .code(200)
                        .message("Course deleted successfully")
                        .data(null)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseById(@PathVariable Long id) {
        CourseResponse response = courseService.getCourseById(id);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.<CourseResponse>builder()
                            .code(404)
                            .message("Course not found")
                            .data(null)
                            .build()
            );
        }
        return ResponseEntity.ok(
                ApiResponse.<CourseResponse>builder()
                        .code(200)
                        .message("")
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> findCourses(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResponse<CourseResponse> response = courseService.findCourses(search, page-1, size);
        return ResponseEntity.ok(
                ApiResponse.<PageResponse<CourseResponse>>builder()
                        .code(200)
                        .message("")
                        .data(response)
                        .build()
        );
    }

}
