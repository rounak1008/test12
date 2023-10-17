package com.vois.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.vois.model.Course;

public interface CourseDAO extends JpaRepository<Course, Long> {

}
