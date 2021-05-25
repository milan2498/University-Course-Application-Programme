package com.cg.mts.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.mts.entities.Applicant;
import com.cg.mts.entities.Course;
import com.cg.mts.entities.Gender;
import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.repository.ICourseRepository;
import com.cg.mts.service.CourseService;

@SpringBootTest
public class TestCourse {

	@Autowired
	private CourseService service;

	@MockBean
	private ICourseRepository repository;

	//test for viewing the course by id
	@Test
	public void viewCourse() {
		Course c = new Course(220, "B.Sc Math", "3 YEARS", LocalDate.now(), LocalDate.now(), "400000");
		Mockito.when(repository.save(c)).thenReturn(c);
		assertNotEquals(c, repository.findById(c.getCourseId()));
	}
	
	//test for viewing all the courses
	@Test
	public void viewAllCoursesTest() {
		Mockito.when(repository.findAll())
				.thenReturn(Stream
						.of(new Course(220, "B.Sc Math", "3 YEARS", LocalDate.now(), LocalDate.now(), "400000"),
								new Course(260, "M.tech Civil", "2 YEARS", LocalDate.now(), LocalDate.now(), "600000"))
						.collect(Collectors.toList()));
		assertEquals(2, service.viewAllCourses().size());
	}

	//test for adding the course
	@Test
	public void addCourseTest() {
		Course c = new Course(220, "B.Sc Math", "3 YEARS", LocalDate.now(), LocalDate.now(), "400000");
		Mockito.when(repository.save(c)).thenReturn(c);
		assertEquals(c, repository.save(c));

	}
	
	//test for removing the course
	@Test
	public void removeCourseTest() {
		Course c = new Course(220, "B.Sc Math", "3 YEARS", LocalDate.now(), LocalDate.now(), "400000");
		assertNotEquals(c, service.removeCourse(220));
	}
	
	//test for updating the course
	@Test
	public void updateCourseTest() {
		Course c = new Course(220, "B.Sc Math", "3 YEARS", LocalDate.now(), LocalDate.now(), "400000");
		Mockito.when(repository.save(c)).thenReturn(c);
		c = new Course(220, "B.Sc Math", "3 YEARS", LocalDate.now(), LocalDate.now(), "400000");
		assertNotEquals(c, repository.save(c));
	}
	
}
