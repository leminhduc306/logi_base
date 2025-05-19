package com.project.logibase.logibase.mapper;

import com.project.logibase.logibase.dto.request.CreateCourseRequest;
import com.project.logibase.logibase.dto.request.UpdateCourseRequest;
import com.project.logibase.logibase.dto.response.CourseResponse;
import com.project.logibase.logibase.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toCourse(CreateCourseRequest request);
    CourseResponse toCourseResponse(Course course);
    Course updateCourse(@MappingTarget Course course, UpdateCourseRequest request);
}
