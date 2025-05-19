package com.project.logibase.logibase.repository;

import com.project.logibase.logibase.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTitle(String title);
    @Query("SELECT c FROM Course c WHERE :search IS NULL OR c.title LIKE %:search% OR c.description LIKE %:search%")
    Page<Course> findCourse(@Param("search") String search, Pageable pageable);}
