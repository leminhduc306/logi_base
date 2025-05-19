package com.project.logibase.logibase.service;

import com.project.logibase.logibase.dto.page.PageResponse;
import com.project.logibase.logibase.dto.request.CreateCourseRequest;
import com.project.logibase.logibase.dto.request.UpdateCourseRequest;
import com.project.logibase.logibase.dto.response.CourseResponse;

import java.awt.print.Pageable;

public interface CourseService {

    void createCourse(CreateCourseRequest request);

    CourseResponse updateCourse (Long id, UpdateCourseRequest request);

    void deleteCourse(Long id);

    CourseResponse getCourseById(Long id);

    PageResponse<CourseResponse> findCourses(String search, int page, int size);
}
