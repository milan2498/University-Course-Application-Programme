package com.cg.mts.controller;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.websocket.Session;

import org.h2.command.ddl.CreateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.exceptions.DataNotFoundException;
import com.cg.mts.exceptions.DuplicateDataException;
import com.cg.mts.exceptions.EmptyDataException;
import com.cg.mts.service.CourseService;
import com.cg.mts.service.JwtUserDetailsService;
import com.cg.mts.service.UniversityStaffService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("/UniversityStaffs")
@JsonIgnoreProperties("Password")
@CrossOrigin(origins = "http://localhost:4200")
public class UniversityStaffController {

	@Autowired
	UniversityStaffService universityService;

	@Autowired
	CourseService courseService;

	@Autowired
	JwtUserDetailsService jwtUserDetailsService;

	@PersistenceContext
	EntityManager entityManager;

	// To Retrieve All the University Staff Members Details
	@GetMapping
	public List<UniversityStaffMember> viewAllStaffs() {
		List<UniversityStaffMember> list = universityService.viewAllStaffs();
		if (list.size() == 0)
			throw new EmptyDataException("No University Staff in Database.");
		return list;
	}

	// To Retrieve Staff Details for the given StaffId
	@GetMapping("/{staffId}")
	public ResponseEntity<?> viewStaff(@PathVariable("staffId") int sid) {
		UniversityStaffMember staff = universityService.viewStaff(sid);
		if (staff == null)
			throw new DataNotFoundException("Request", "Staff with id " + sid + " not found");
		else
			return new ResponseEntity<UniversityStaffMember>(staff, HttpStatus.OK);
	}

	// To Save University Staff Details
	@PostMapping
	public String addStaff(@Valid @RequestBody UniversityStaffMember staff) {
		//System.out.println(staff);
		if (universityService.viewStaff(staff.getStaffId()) == null) 
		{
			universityService.addStaff(staff);
			return "Data saved Succesfully";
		}
		else
		throw new DuplicateDataException("Duplicate Staff Id");
	}

	// To Update the Staff Details
	@PutMapping
	public String updateStaff(@Valid @RequestBody UniversityStaffMember staff) {
		if (universityService.updateStaff(staff))
			return "Staff data updated";
		else
			throw new DataNotFoundException("Update", "Staff with id " + staff.getStaffId() + " not found");
	}

	// To Delete the Staff Details
	@DeleteMapping("/{staffId}")
	public String removeStaff(@PathVariable("staffId") int sId) {
		if (universityService.removeStaff(sId))
			return "Staff Data with id "+sId+" Deleted Succesfully";
		else
			throw new DataNotFoundException("Delete", "Staff with id " + sId + " not found");
	}

	// To Retrieve Course Details using Course ID
	@GetMapping("/findCourseDetailsWithCourseID/{courseId}")
	public ResponseEntity<?> getCourse(@PathVariable("courseId") int id) {
		// UniversityStaffMember staff=universityService.viewStaff(sid);
		Course c = courseService.viewCourse(id);
		if (c == null)
			throw new DataNotFoundException("Request", "course with id " + id + " not found.");
		else
			return new ResponseEntity<Course>(c, HttpStatus.OK);
	}

	// To Retrieve Course Details of all the Courses Under a Staff
	@GetMapping("/findCoursesUnderStaffID/{staffID}")
	public Set<Course> viewAllCourses(@PathVariable("staffID") int id) {
		Set<Course> set = universityService.viewAllCoursesUnderThisStaffId(id);
		if (set.size() == 0)
			throw new EmptyDataException("No Courses under this Staff ID.");
		return set;
	}

	// To add a Course By a Staff
	@PostMapping("/addCourseByStaff/{staffId}")
	public String addCourse(@RequestHeader("Authorization") String token, @Valid @PathVariable("staffId") int sId,
			@RequestBody Course c) {
		String role = jwtUserDetailsService.getRoleFromToken(token);
		if (courseService.viewCourse(c.getCourseId()) == null) {
			if (role.contentEquals("STAFF")) {
				universityService.addCourse(c, sId);
				return "Course Details saved Succesfully";
			}
			else {
				return "Invalid role!";
			}
		}
		return "Duplicate Course ID";
	}

	// To Update Course Details By a Staff
	/*@PutMapping("/updateCourseDetails/{courseId}")
	public String updateCourse(@RequestHeader("Authorization") String token, @Valid @RequestBody Course c,
			@PathVariable("courseId") int cid) {
		String role = jwtUserDetailsService.getRoleFromToken(token);
		if (role.equalsIgnoreCase("STAFF")) {
			if (courseService.updateCourse(c, cid))
				return "data updated";
			else
				throw new DataNotFoundException("Update", "Course with id" + c.getCourseId() + "not found");
		} else {
			return "Invalid role!";
		}
	}*/

	// Delete Course Data
	@DeleteMapping("/deleteCourseUsingCourseID/{courseId}")
	public String removeCourseByStaff(@RequestHeader("Authorization") String token, @PathVariable("courseId") int id) {
		String role = jwtUserDetailsService.getRoleFromToken(token);
		if (role.equalsIgnoreCase("STAFF")) {
			if (courseService.removeCourse(id))
				return "data deleted";
			else
				throw new DataNotFoundException("Delete", "Course with id " + id + " not found");
		} else {
			return "Invalid role!";
		}

	}

}
