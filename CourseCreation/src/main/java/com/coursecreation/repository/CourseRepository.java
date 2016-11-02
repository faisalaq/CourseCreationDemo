package com.coursecreation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursecreation.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
