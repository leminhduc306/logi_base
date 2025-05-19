package com.project.logibase.logibase.service.impl;

import com.project.logibase.logibase.constant.ErrorCode;
import com.project.logibase.logibase.dto.page.PageResponse;
import com.project.logibase.logibase.dto.request.CreateCourseRequest;
import com.project.logibase.logibase.dto.request.UpdateCourseRequest;
import com.project.logibase.logibase.dto.response.CourseResponse;
import com.project.logibase.logibase.entity.Course;
import com.project.logibase.logibase.exception.AppException;
import com.project.logibase.logibase.mapper.CourseMapper;
import com.project.logibase.logibase.repository.CourseRepository;
import com.project.logibase.logibase.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor

public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    private CourseMapper courseMapper;

    @Override
    public void createCourse(CreateCourseRequest request) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if(courseRepository.existsByTitle(request.getTitle())){
            throw new AppException(ErrorCode.TITLE_ALREADY_EXIST);
        }
        if(request.getTitle() == null || request.getTitle().isEmpty()){
            throw new AppException(ErrorCode.TITLE_NOT_NULL);
        }

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setPrice(BigDecimal.ZERO);
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());
        courseRepository.save(course);
    }

    @Override
    public CourseResponse updateCourse(Long id, UpdateCourseRequest request) {

        if(courseRepository.existsByTitle(request.getTitle())){
            throw new AppException(ErrorCode.TITLE_ALREADY_EXIST);
        }
        if(request.getTitle() == null || request.getTitle().isEmpty()){
            throw new AppException(ErrorCode.TITLE_NOT_NULL);
        }
        if(request.getPrice().compareTo(BigDecimal.ZERO) < 0 ){
            throw new AppException(ErrorCode.PRICE_LOWER_ZERO);
        }

        Course course = courseRepository.findById(id).orElse(null);

        if(course == null){
            throw new AppException(ErrorCode.COURSE_NOT_EXIST);
        }

        courseMapper.updateCourse(course, request);
        courseRepository.save(course);
        return courseMapper.toCourseResponse(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if(course != null){
            return courseMapper.toCourseResponse(course);
        }
        return null;
    }

    @Override
    public PageResponse<CourseResponse> findCourses(String search, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Course> coursePage = courseRepository.findCourse(search, pageable);

        Page<CourseResponse> responsePage = coursePage.map(courseMapper::toCourseResponse);

        return new PageResponse<>(responsePage);
    }

}
