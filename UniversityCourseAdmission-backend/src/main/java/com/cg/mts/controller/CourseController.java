package com.cg.mts.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Applicant;
import com.cg.mts.entities.Course;
import com.cg.mts.exceptions.DataNotFoundException;
import com.cg.mts.exceptions.EmptyDataException;
import com.cg.mts.service.CourseService;
import com.cg.mts.service.JwtUserDetailsService;

@RestController
@RequestMapping("/Courses")
@CrossOrigin(origins = "http://localhost:4200")

public class CourseController {

	@Autowired
	CourseService service;

	@Autowired
	JwtUserDetailsService jwtUserDetailsService;

	// Authorize before access
	@DeleteMapping("/{cid}")
	public String removeCourse(/*@RequestHeader("Authorization") String token,*/ @PathVariable("cid") int id) {

		/*String role = jwtUserDetailsService.getRoleFromToken(token);
		if (role.equalsIgnoreCase("STAFF")) {*/
			if (service.removeCourse(id))
				return "data with with id "+id+" Deleted Succesfully";
			else
				throw new DataNotFoundException("Delete", "Course with id to delete " + id + "not found");
		} /*else {
			return "Invalid role!";
		}*/

	

	/*@PostMapping("{cid}")
	public String addCourse(@RequestHeader("Authorization") String token, @Valid @RequestBody Course c,
			@PathVariable("sid") int id) {
		String role = jwtUserDetailsService.getRoleFromToken(token);
		if (role.contentEquals("STAFF")) {
			service.addCourse(c, id);
			return "Course successsfully added";

		} else {
			return "Invalid role!";
		}

	}*/
	@PostMapping
	public String saveApplicant(@Valid @RequestBody Course c) {
		service.addCourse(c);
		return "data saved";	
	}

	@PutMapping
	public String updateCourse(/*@RequestHeader("Authorization") String token,*/ @Valid @RequestBody Course c) {
		/*String role = jwtUserDetailsService.getRoleFromToken(token);
		if (role.equalsIgnoreCase("STAFF")) {*/
			if (service.updateCourse(c))
				return "data updated";
			else
				throw new DataNotFoundException("Update", "Course with id" + c.getCourseId() + "not found");
		} /*else {
			return "Invalid role!";
		}

	}*/

	@GetMapping("{cid}")
	public ResponseEntity<?> viewcourse(@PathVariable("cid") int courseId) {
		Course c = service.viewCourse(courseId);
		if (c == null)
			throw new DataNotFoundException("Request", "Course with id " + courseId + "not found");
		return new ResponseEntity<Course>(c, HttpStatus.OK);
	}

	@GetMapping("/ShowOnlyBTechCourses")
	public List<Course> ShowBTechCourse() {
		List<Course> list = service.ShowBTechCourse();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

	@GetMapping("/ShowOnlyBScCourses")
	public List<Course> ShowBScCourse() {
		List<Course> list = service.ShowBScCourse();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

	@GetMapping("/ShowOnlyMTECHCourses")
	public List<Course> showMTechCourse() {
		List<Course> list = service.showMTechCourse();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

	@GetMapping("/Show4YearCourses")
	public List<Course> year4CourseDuration() {
		List<Course> list = service.year4CourseDuration();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

	@GetMapping("/Show3YearCourses")
	public List<Course> year3CourseDuration() {
		List<Course> list = service.year3CourseDuration();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

	@GetMapping("/Show2YearCourses")
	public List<Course> year2CourseDuration() {
		List<Course> list = service.year2CourseDuration();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

	@GetMapping("/Show5LacFeesCourses")
	public List<Course> fees5LacCourses() {
		List<Course> list = service.fees5LacCourses();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

	@GetMapping("/Show8LacFeesCourses")
	public List<Course> fees8LacCourses() {
		List<Course> list = service.fees8LacCourses();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

	@GetMapping("/Show4LacFeesCourses")
	public List<Course> fees4LacCourses() {
		List<Course> list = service.fees4LacCourses();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

	@GetMapping
	public List<Course> viewAllCourse() {
		List<Course> list = service.viewAllCourses();
		if (list.size() == 0)
			throw new EmptyDataException("No Courses in database");
		return list;
	}

}