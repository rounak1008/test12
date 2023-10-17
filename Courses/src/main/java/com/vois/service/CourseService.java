package com.vois.service;

import java.util.List;

import com.vois.model.Course;

public interface CourseService {
	public List<Course> getCourses();
	public Course getCourse(Long courseId);
	public Course addCourse(Course course);
	public Course updateCourse(Course course,Long courseId);
	public Course deleteCourse(Long courseId);
}
