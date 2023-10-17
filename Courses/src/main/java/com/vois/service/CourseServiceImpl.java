package com.vois.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vois.dao.CourseDAO;
import com.vois.model.Course;

@Service
public class CourseServiceImpl implements CourseService {
	
	//private static Long countCourse;
	@Autowired
	private CourseDAO courseDAO;
	
	
	@Override
	public List<Course> getCourses() {
		
		return courseDAO.findAll();
	}


	@Override
	public Course getCourse(Long courseId) {
		Course c=null;
		try {
			c=courseDAO.findById(courseId).get();
		}catch(Exception e) {
			
		}
		return c;
	}


	@Override
	public Course addCourse(Course course) {
		return courseDAO.save(course);
	}


	@Override
	public Course updateCourse(Course course,Long courseId) {
		return courseDAO.save(course);
	}


	@Override
	public Course deleteCourse(Long courseId) {
		
		Course c=courseDAO.findById(courseId).get();
		courseDAO.delete(c);
		return c;
	}



}
