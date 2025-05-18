package com.project.logibase.logibase.service;

import com.project.logibase.logibase.dto.request.CreateCourseRequest;
import com.project.logibase.logibase.dto.request.UpdateCourseRequest;
import com.project.logibase.logibase.dto.response.CourseResponse;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);

    CourseResponse updateCourse(UpdateCourseRequest request);

    void deleteCourse(Long id);

    CourseResponse getCourseById(Long id);

    Page<CourseResponse> getCourses(Pageable pageable);
}
