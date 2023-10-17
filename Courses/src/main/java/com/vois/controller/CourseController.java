package com.vois.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vois.model.Course;
import com.vois.service.CourseService;


@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;// = new CourseServiceImpl();
	
	protected static final Logger logger=LoggerFactory.getLogger(CourseController.class);
	
	
	@GetMapping("/home")
	public String myHome() {
		return "home screen";
	}
	
	@GetMapping("/welcome")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String myWelcome() {
		return "This is welcome message";
	}

	 //Get All Courses
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getCourses(){
		logger.error("Course Controller :: getCourses method called (list):: start");
		List<Course> list=courseService.getCourses();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		logger.error("Course Controller :: getCourses method called (list):: end");
		return ResponseEntity.of(Optional.of(list));
	}
	
	//Get a course with a particular course
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable String courseId) {
		logger.error("Course Controller :: getCourse method called (single) :: start");
		Course course=this.courseService.getCourse(Long.parseLong(courseId));
		
		if(course==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		logger.error("Course Controller :: getCourse method called (single) :: end");
		return ResponseEntity.of(Optional.of(course));
	}
	
	//Add a new course
	@PostMapping(path="/courses")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		logger.error("Course Controller :: addCourse method called (single) :: start");
		Course c=null;
		try {
			c=this.courseService.addCourse(course);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		logger.error("Course Controller :: addCourse method called (single) :: end");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	//update a course
	@PutMapping("/courses/{courseId}")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course,@PathVariable("courseId") Long courseId) {
		logger.error("Course Controller :: updateCourse method called (single) :: start");
		Course c=null;
		try {
			c=this.courseService.updateCourse(course,courseId);
			return ResponseEntity.ok(c);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		logger.error("Course Controller :: updateCourse method called (single) :: end");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<Course> deleteCourse(@PathVariable("courseId") Long courseId) {
		logger.error("Course Controller :: deleteCourse method called (single) :: start");
		Course c=null;
		try {
			c=this.courseService.deleteCourse(courseId);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		logger.error("Course Controller :: deleteCourse method called (single) :: end");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
