package com.project.logibase.logibase.service.impl;

import com.project.logibase.logibase.dto.request.CreateCourseRequest;
import com.project.logibase.logibase.dto.request.UpdateCourseRequest;
import com.project.logibase.logibase.dto.response.CourseResponse;
import com.project.logibase.logibase.entity.Course;
import com.project.logibase.logibase.mapper.CourseMapper;
import com.project.logibase.logibase.repository.CourseRepository;
import com.project.logibase.logibase.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@AllArgsConstructor

public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    private CourseMapper courseMapper;

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {
        Course course = courseMapper.toCourse(request);
        Course saved = courseRepository.save(course);
        return courseMapper.toCourseResponse(saved);    }

    @Override
    public CourseResponse updateCourse(UpdateCourseRequest request) {
        return null;
    }

    @Override
    public void deleteCourse(Long id) {

    }

    @Override
    public CourseResponse getCourseById(Long id) {
        return null;
    }

    @Override
    public Page<CourseResponse> getCourses(Pageable pageable) {
        return null;
    }
}
